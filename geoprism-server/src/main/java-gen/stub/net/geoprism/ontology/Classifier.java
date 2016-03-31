/**
 * Copyright (c) 2015 TerraFrame, Inc. All rights reserved.
 *
 * This file is part of Runway SDK(tm).
 *
 * Runway SDK(tm) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Runway SDK(tm) is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Runway SDK(tm).  If not, see <http://www.gnu.org/licenses/>.
 */
package net.geoprism.ontology;

import java.sql.Savepoint;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.business.Relationship;
import com.runwaysdk.business.ontology.OntologyStrategyIF;
import com.runwaysdk.business.ontology.Term;
import com.runwaysdk.business.ontology.TermAndRel;
import com.runwaysdk.dataaccess.BusinessDAOIF;
import com.runwaysdk.dataaccess.DuplicateDataException;
import com.runwaysdk.dataaccess.MdAttributeTermDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.database.BusinessDAOFactory;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.AttributeReference;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.metadata.ontology.DatabaseAllPathsStrategy;

public class Classifier extends ClassifierBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long   serialVersionUID = 1158111601;

  private static final String KEY_CONCATENATOR = ".";

  public Classifier()
  {
    super();
  }

  /**
   * Persists this Classifier, performs validation on its values, and generates a UniqueId from the display label, if
   * necessary.
   */
  @Override
  @Transaction
  public void apply()
  {
    // If they didn't specify a UniqueId we can figure one out from the DisplayLabel
    if (this.getClassifierId() == null || this.getClassifierId().length() == 0)
    {
      String uniqueId = this.getDisplayLabel().getValue().trim().replaceAll("\\s+", "");
      this.setClassifierId(uniqueId);
    }

    super.apply();

    if (this.isNew())
    {
      this.addTerm(ClassifierIsARelationship.CLASS);
    }
  }

  public static String buildKey(String pkg, String id)
  {
    return pkg + KEY_CONCATENATOR + id;
  }

  @Override
  public String buildKey()
  {
    return Classifier.buildKey(this.getClassifierPackage(), this.getClassifierId());
  }

  @Override
  @Transaction
  public void delete()
  {
    ClassifierProblem.deleteProblems(this);

    super.delete();
  }

  /**
   * Returns the <code>Classifier</code> object with a label or synonym that matches the given term. Searches all nodes
   * that are children of the given attribute root nodes including the root nodes.
   * 
   * @param sfTermToMatch
   * @param mdAttributeTermDAO
   * @return the <code>Classifier</code> object with a label or synonym that matches the given term.
   */
  public static Classifier findMatchingTerm(String sfTermToMatch, MdAttributeTermDAOIF mdAttributeTermDAOIF)
  {
    QueryFactory qf = new QueryFactory();

    ClassifierQuery classifierRootQ = new ClassifierQuery(qf);
    ClassifierTermAttributeRootQuery carQ = new ClassifierTermAttributeRootQuery(qf);
    ClassifierQuery classifierQ = new ClassifierQuery(qf);
    ClassifierAllPathsTableQuery allPathsQ = new ClassifierAllPathsTableQuery(qf);
    ClassifierSynonymQuery synonymQ = new ClassifierSynonymQuery(qf);

    carQ.WHERE(carQ.getParent().EQ(mdAttributeTermDAOIF));

    classifierRootQ.WHERE(classifierRootQ.classifierTermAttributeRoots(carQ));

    allPathsQ.WHERE(allPathsQ.getParentTerm().EQ(classifierRootQ));

    synonymQ.WHERE(synonymQ.getDisplayLabel().localize().EQ(sfTermToMatch));

    classifierQ.WHERE(OR.get(classifierQ.getDisplayLabel().localize().EQ(sfTermToMatch), classifierQ.hasSynonym(synonymQ)).AND(classifierQ.EQ(allPathsQ.getChildTerm())));
    
    OIterator<? extends Classifier> i = classifierQ.getIterator();
    try
    {
      for (Classifier classifier : i)
      {
        return classifier;
      }
    }
    finally
    {
      i.close();
    }
    return null;
  }

  /**
   * MdMethod used for creating Classifiers.
   * 
   * @param termId
   * @param name
   * @return
   */
  @Transaction
  public static TermAndRel create(Classifier dto, String parentId)
  {
    Classifier parent = Classifier.get(parentId);

    // If they didn't specify a package we can attempt to figure one out for them.
    if (dto.getClassifierPackage() == null || dto.getClassifierPackage().length() == 0)
    {
      String camelUniqueId = StringUtils.uncapitalize(parent.getClassifierId().trim().replaceAll("\\s+", ""));

      if (Classifier.getRoot().equals(parent))
      {
        dto.setClassifierPackage(camelUniqueId);
      }
      else
      {
        dto.setClassifierPackage(parent.getClassifierPackage() + KEY_CONCATENATOR + camelUniqueId);
      }
    }

    dto.apply();

    Relationship rel = dto.addLink(parent, ClassifierIsARelationship.CLASS);

    return new TermAndRel(dto, ClassifierIsARelationship.CLASS, rel.getId());
  }

  public static OntologyStrategyIF createStrategy()
  {
    return new DatabaseAllPathsStrategy();
  }

  public static Classifier getRoot()
  {
    return (Classifier) Term.getRoot(Classifier.CLASS);
  }

  /**
   * Returns the JSONObject representation of the node and all of its children nodes
   * 
   * @return
   */
  public JSONObject getJSONObject()
  {
    try
    {
      JSONArray children = new JSONArray();
      OIterator<Term> iterator = null;

      try
      {
        iterator = this.getDirectDescendants(ClassifierIsARelationship.CLASS);

        List<Term> terms = iterator.getAll();

        for (Term term : terms)
        {
          Classifier child = (Classifier) term;

          children.put(child.getJSONObject());
        }

      }
      finally
      {
        if (iterator != null)
        {
          iterator.close();
        }
      }

      JSONObject object = new JSONObject();
      object.put("label", this.getDisplayLabel().getValue());
      object.put("id", this.getId());
      object.put("type", this.getType());
      object.put("children", children);
      object.put("fetched", true);

      return object;
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  /**
   * Finds the classifier with the given label for the given term attribute. If the classifier does not exist, then it
   * is created.
   * 
   * @param classifierLabel
   * @param mdAttributeTermDAO
   * @return
   */
  @Transaction
  public static Classifier findClassifierAddIfNotExist(String packageString, String classifierLabel, MdAttributeTermDAOIF mdAttributeTermDAO)
  {
    return Classifier.findClassifierAddIfNotExist(packageString, classifierLabel, mdAttributeTermDAO, false);
  }

  /**
   * Finds the classifier with the given label for the given term attribute. If the classifier does not exist, then it
   * is created.
   * 
   * @param packageString
   * @param classifierLabel
   * @param mdAttributeTermDAO
   * @param createProblem
   *          Flag indicating if a ClassifierProblem should be created when a new classifier is created
   * @return
   */
  @Transaction
  public static Classifier findClassifierAddIfNotExist(String packageString, String classifierLabel, MdAttributeTermDAOIF mdAttributeTermDAO, boolean createProblem)
  {
    Classifier classifier = findMatchingTerm(classifierLabel, mdAttributeTermDAO);

    if (classifier == null)
    {
      classifier = new Classifier();
      classifier.getDisplayLabel().setDefaultValue(classifierLabel);
      classifier.setClassifierId(classifierLabel);
      classifier.setClassifierPackage(packageString);
      classifier.apply();

      // Create a new Classifier problem
      if (createProblem)
      {
        ClassifierProblem.createProblems(classifier, ClassifierProblemType.UNMATCHED);
      }

      QueryFactory qf = new QueryFactory();
  
      ClassifierQuery classifierRootQ = new ClassifierQuery(qf);
      ClassifierTermAttributeRootQuery carQ = new ClassifierTermAttributeRootQuery(qf);
  
      carQ.WHERE(carQ.parentId().EQ(mdAttributeTermDAO.getId()));

      classifierRootQ.WHERE(classifierRootQ.classifierTermAttributeRoots(carQ));
  
      OIterator<? extends Classifier> i = classifierRootQ.getIterator();
      try
      {
        for (Classifier classifierRoot : i)
        {
          classifier.addLink(classifierRoot, ClassifierIsARelationship.CLASS);
        }
      }
      finally
      {
        i.close();
      }
    }

    return classifier;
  }

  @Transaction
  public static String[] makeSynonym(String sourceId, String destinationId)
  {
    Classifier source = Classifier.get(sourceId);
    Classifier destination = Classifier.get(destinationId);

    ClassifierIsARelationshipQuery query = new ClassifierIsARelationshipQuery(new QueryFactory());
    query.WHERE(query.getChild().EQ(source));

    OIterator<? extends ClassifierIsARelationship> iterator = query.getIterator();

    List<String> ids = new LinkedList<String>();

    try
    {
      while (iterator.hasNext())
      {
        ClassifierIsARelationship locatedIn = iterator.next();

        ids.add(locatedIn.getParentId());
      }
    }
    finally
    {
      iterator.close();
    }

    makeSynonym(source, destination);

    ids.add(destinationId);

    return ids.toArray(new String[ids.size()]);
  }

  @Transaction
  public static ClassifierSynonym makeSynonym(Classifier source, Classifier destination)
  {
    // Copy over all synonyms
    ClassifierHasSynonymQuery query = new ClassifierHasSynonymQuery(new QueryFactory());
    query.WHERE(query.getParent().EQ(source));

    OIterator<? extends ClassifierHasSynonym> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        ClassifierHasSynonym sRelationship = it.next();

        ClassifierSynonym sSynonymn = sRelationship.getChild();
        String synonymName = sSynonymn.getDisplayLabel().getValue();

        createSynonym(destination, synonymName);

        sSynonymn.delete();
      }
    }
    finally
    {
      it.close();
    }

    // Delete all problems for the source geo classifier so that they aren't transfered over to the destination
    // classifier
    ClassifierProblemQuery problemQuery = new ClassifierProblemQuery(new QueryFactory());
    problemQuery.WHERE(problemQuery.getClassifier().EQ(source));

    OIterator<? extends ClassifierProblem> iterator = problemQuery.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        ClassifierProblem problem = iterator.next();
        problem.delete();
      }
    }
    finally
    {
      iterator.close();
    }

    // Copy over any synonyms to the destination and delete the originals
    BusinessDAOIF sourceDAO = (BusinessDAOIF) BusinessFacade.getEntityDAO(source);

    BusinessDAOFactory.floatObjectIdReferences(sourceDAO.getBusinessDAO(), source.getId(), destination.getId(), true);

    ClassifierSynonym synonym = createSynonym(destination, source.getDisplayLabel().getValue());

    source.delete();

    return synonym;
  }

  private static ClassifierSynonym createSynonym(Classifier destination, String synonymName)
  {
    Savepoint savepoint = Database.setSavepoint();

    try
    {
      ClassifierSynonym synonym = new ClassifierSynonym();
      synonym.getDisplayLabel().setValue(synonymName);

      ClassifierSynonym.createSynonym(synonym, destination.getId());

      return synonym;
    }
    catch (DuplicateDataException e)
    {
      // Rollback the savepoint
      Database.rollbackSavepoint(savepoint);

      savepoint = null;
    }
    finally
    {
      if (savepoint != null)
      {
        Database.releaseSavepoint(savepoint);
      }
    }

    return null;
  }

  public static String getClassifierTree(String classifierId)
  {
    Classifier root = Classifier.getRoot();

    Classifier classifier = Classifier.get(classifierId);

    OIterator<Term> anscestorIt = classifier.getAllAncestors(ClassifierIsARelationship.CLASS);
    Set<String> ids = new HashSet<String>();

    try
    {
      while (anscestorIt.hasNext())
      {
        Classifier ancestor = (Classifier) anscestorIt.next();

        if (!ancestor.getId().equals(root.getId()))
        {
          ids.add(ancestor.getId());
        }
      }
    }
    finally
    {
      anscestorIt.close();
    }

    JSONArray array = new JSONArray();

    QueryFactory factory = new QueryFactory();

    ClassifierIsARelationshipQuery isAQuery = new ClassifierIsARelationshipQuery(factory);
    isAQuery.WHERE(isAQuery.getParent().EQ(root));
    isAQuery.AND(isAQuery.childId().IN(ids.toArray(new String[ids.size()])));

    ClassifierQuery query = new ClassifierQuery(factory);
    query.WHERE(query.isAParent(isAQuery));
    query.ORDER_BY_ASC(query.getDisplayLabel().localize());

    OIterator<? extends Classifier> classifierIt = query.getIterator();

    try
    {
      while (classifierIt.hasNext())
      {
        Classifier parent = classifierIt.next();

        array.put(Classifier.getJSONObject(parent, ids, true));
      }
    }
    finally
    {
      classifierIt.close();
    }

    return array.toString();
  }

  /**
   * Returns the JSONObject representation of the node and all of its children nodes
   * 
   * @param _classifier
   *          Entity to serialize into a JSONObject
   * @param _ids
   *          List of classifier ids in which to include the children in the serialization
   * @param _isRoot
   *          Flag indicating if the classifier represents a root node of the tree
   * @return
   */
  private static JSONObject getJSONObject(Classifier _classifier, Set<String> _ids, boolean _isRoot)
  {
    try
    {
      JSONArray children = new JSONArray();

      if (_ids.contains(_classifier.getId()))
      {
        OIterator<? extends ClassifierIsARelationship> iterator = null;

        try
        {
          // Get the relationships where this object is the parent

          ClassifierIsARelationshipQuery query = new ClassifierIsARelationshipQuery(new QueryFactory());
          query.WHERE(query.getParent().EQ(_classifier));
          query.ORDER_BY_ASC( ( (AttributeReference) query.getChild() ).aLocalCharacter(Classifier.DISPLAYLABEL).localize());
          iterator = query.getIterator();

          List<? extends ClassifierIsARelationship> relationships = iterator.getAll();

          for (ClassifierIsARelationship relationship : relationships)
          {
            Classifier child = relationship.getChild();

            JSONObject parentRecord = new JSONObject();
            parentRecord.put("parentId", relationship.getParentId());
            parentRecord.put("relId", relationship.getId());
            parentRecord.put("relType", ClassifierIsARelationship.CLASS);

            JSONObject object = Classifier.getJSONObject(child, _ids, false);
            object.put("parentRecord", parentRecord);

            children.put(object);
          }

        }
        finally
        {
          if (iterator != null)
          {
            iterator.close();
          }
        }
      }

      String label = _classifier.getDisplayLabel().getValue();

      JSONObject object = new JSONObject();
      object.put("label", label);
      object.put("id", _classifier.getId());
      object.put("type", _classifier.getType());
      object.put("children", children);
      object.put("fetched", ( children.length() > 0 ));

      return object;
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  public static ClassifierProblemView[] getAllProblems()
  {
    List<ClassifierProblemView> list = new LinkedList<ClassifierProblemView>();

    ClassifierProblemQuery query = new ClassifierProblemQuery(new QueryFactory());
    query.ORDER_BY_DESC(query.getProblemType().getDisplayLabel().localize());
    query.ORDER_BY_ASC(query.getClassifier().getDisplayLabel().localize());

    OIterator<? extends ClassifierProblem> iterator = query.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        ClassifierProblem problem = iterator.next();

        list.addAll(problem.getViews());
      }
    }
    finally
    {
      iterator.close();
    }

    return list.toArray(new ClassifierProblemView[list.size()]);
  }

  @Transaction
  public static void deleteClassifierProblem(String problemId)
  {
    ClassifierProblem problem = ClassifierProblem.get(problemId);
    problem.delete();
  }

  public static Classifier findClassifier(String classifierPackage, String classifierId)
  {
    ClassifierQuery query = new ClassifierQuery(new QueryFactory());
    query.WHERE(query.getClassifierPackage().EQ(classifierPackage));
    query.WHERE(query.getClassifierId().EQ(classifierId));

    OIterator<? extends Classifier> it = null;

    try
    {
      it = query.getIterator();

      if (it.hasNext())
      {
        Classifier classifier = it.next();

        return classifier;
      }

      return null;
    }
    finally
    {
      if (it != null)
      {
        it.close();
      }
    }
  }

}