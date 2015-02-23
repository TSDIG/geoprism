/*
 * Copyright (c) 2013 TerraFrame, Inc. All rights reserved.
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

//define(["../../ClassFramework", "../../Util", "../RunwaySDK_UI", "../factory/runway/widget/Widget", "../datatable/datasource/InstanceQueryDataSource"], function(ClassFramework, Util, UI, Widget, InstanceQueryDataSource) {
(function(){  

  var Util = Mojo.Util;
  var ClassFramework = Mojo.Meta;
  var Widget = com.runwaysdk.ui.factory.runway.Widget;
  
  var geoentityTreeName = "com.runwaysdk.geodashboard.ontology.GeoEntityTree";
  var TermTree = com.runwaysdk.geodashboard.ontology.TermTree;
  
  /**
   * LANGUAGE
   */
  com.runwaysdk.Localize.defineLanguage(geoentityTreeName, {
    "deleteDescribe" : "Are you sure you want to delete '${termLabel}' and all of its children?",
    "synonymsNode" : "Synonyms",
    "viewSynonyms" : "View Synonyms",
    "hideSynonyms" : "Hide Synonyms",
    "createSynonym" : "Create Synonym",
    "updateSynonym" : "Update Synonym",
    "deleteSynonym" : "Delete Synonym",
    "refreshSynonyms" : "Refresh Synonyms",
    "emptyMessage" : "No data to display, create a Universal first."
//    "exportAll" : "Export All"
  });
  
  /**
   * 
   */
  var geoentityTree = ClassFramework.newClass(geoentityTreeName, {
    
    Extends : TermTree,
    
    Instance : {
      
      initialize : function(config) {
        config = config || {};
        
        config.exportMenuType = "com.runwaysdk.geodashboard.gis.GeoEntityExportMenu";
        
        this.$initialize(config);
        
        this._wrapperDiv = this.getFactory().newElement("div");
      },
      
      /**
       * is binded to tree.contextmenu, called when the user right clicks on a node.
       * This override will disable some context menu options that don't make sense.
       */
      // @Override
      __onNodeRightClick : function(event, object) {
        var that = this;
        var node = event.node;
        var parentId = this.__getRunwayIdFromNode(node.parent);
        var term = this.termCache[this.__getRunwayIdFromNode(node)];
        
        that.getImpl().tree('selectNode', node);
        
        if (this._cm != null && !this._cm.isDestroyed()) {
          this._cm.destroy();
        }
        
        this._cm = this.getFactory().newContextMenu(node);
        
        if (node.data != null && node.data.isSynonymContainer) {
          // They right clicked on a synonym container node. Display the context menu for synonym containers.
          var create = this._cm.addItem(this.localize("createSynonym"), "add", Mojo.Util.bind(this, this.__onCreateSynonym));
          var refresh = this._cm.addItem(this.localize("refreshSynonyms"), "refresh", Mojo.Util.bind(this, this.__onRefreshSynonym));
        }
        else if (node.data != null && node.data.isSynonym) {
          // Display the synonym node context menu.
          var delSyn = this._cm.addItem(this.localize("updateSynonym"), "edit", Mojo.Util.bind(this, this.__onUpdateSynonym));
          var update = this._cm.addItem(this.localize("deleteSynonym"), "delete", Mojo.Util.bind(this, this.__onDeleteSynonym));
        }
        else {
          // Display the standard context menu but disable some options based on GeoEntity rules.
          
          var create = this._cm.addItem(this.localize("create"), "add", Mojo.Util.bind(this, this.__onContextCreateClick));
          if (term.canCreateChildren === false) {
            create.setEnabled(false);
          }
          
          var update = this._cm.addItem(this.localize("update"), "edit", Mojo.Util.bind(this, this.__onContextEditClick));
          
          var del = this._cm.addItem(this.localize("delete"), "delete", Mojo.Util.bind(this, this.__onContextDeleteClick));
          if (parentId === this.rootTermId) {
            del.setEnabled(false);
          }
          
          var refresh = this._cm.addItem(this.localize("refresh"), "refresh", Mojo.Util.bind(this, this.__onContextRefreshClick));
          
          var synonyms = null;
          if (node.data == null) { node.data = {}; }
          if (node.data.synonymNode != null) {
            synonyms = this._cm.addItem(this.localize("hideSynonyms"), "synonyms", Mojo.Util.bind(this, this.__onHideSynonym));
          }
          else {
            synonyms = this._cm.addItem(this.localize("viewSynonyms"), "synonyms", Mojo.Util.bind(this, this.__onContextViewSynonymsClick));
          }
          
          var cmiExport = this._cm.addItem(this.localize("export"), "export", Mojo.Util.bind(this, this.__onContextExportClick));
          
          if (this.isNodeBusy(node)) {
            create.setEnabled(false);
            update.setEnabled(false);
            del.setEnabled(false);
            refresh.setEnabled(false);
            synonyms.setEnabled(false);
            cmiExport.setEnabled(false);
          }
        }
        
        this._cm.render();
        
        this._cm.addDestroyEventListener(function() {
          that.getImpl().tree("selectNode", null);
        });
      },
      
      __onCreateSynonym : function(contextMenu, contextMenuItem, mouseEvent) {
        var that = this;
        var targetNode = contextMenu.getTarget();
        var parentId = this.__getRunwayIdFromNode(targetNode.parent);
        
        new com.runwaysdk.ui.RunwayControllerFormDialog({
          type: "com.runwaysdk.system.gis.geo.Synonym",
          action: "create",
          actionParams: {geoId: parentId},
          width: 730,
          height: 200,
          onSuccess : function(tnr) {
            var syn = tnr.getTerm();
            that.termCache[syn.getId()] = syn;
            that.parentRelationshipCache.put(syn.getId(), {parentId: parentId, relId: tnr.getRelationshipId(), relType: tnr.getRelationshipType()});
            that.__createTreeNode(syn.getId(), targetNode, true, {data: {isSynonym: true}});
          },
          onFailure : function(e) {
            that.handleException(e);
          }
        }).render();
      },
      __onHideSynonym : function(contextMenu, contextMenuItem, mouseEvent) {
        var targetNode = contextMenu.getTarget();
        
        this.getImpl().tree("removeNode", targetNode.data.synonymNode);
        targetNode.data.synonymNode = null;
      },
      __onRefreshSynonym : function(contextMenu, contextMenuItem, mouseEvent) {
        var targetNode = contextMenu.getTarget();
        
        this.refreshTerm(this.__getRunwayIdFromNode(targetNode));
      },
      __onUpdateSynonym : function(contextMenu, contextMenuItem, mouseEvent) {
        var that = this;
        var targetNode = contextMenu.getTarget();
        var targetId = this.__getRunwayIdFromNode(targetNode);
        
        this.setNodeBusy(targetNode, true);
        
        new com.runwaysdk.ui.RunwayControllerFormDialog({
          type: "com.runwaysdk.system.gis.geo.Synonym",
          action: "update",
          viewParams: {id: targetId},
          width: 730,
          height: 200,
          onSuccess : function(syn) {
            that.termCache[syn.getId()] = syn;
            
            var nodes = that.__getNodesById(targetId);
            for (var i = 0; i < nodes.length; ++i) {
              $(that.getRawEl()).tree("updateNode", nodes[i], {label: that._getTermDisplayLabel(syn), id: syn.getId(), runwayId: syn.getId()});
            }
            
            that.setNodeBusy(targetNode, false);
          },
          onFailure : function(e) {
            that.setNodeBusy(targetNode, false);
            that.handleException(e);
          },
          onCancel : function() {
            that.setNodeBusy(targetNode, false);
          }
        }).render();
      },
      __onDeleteSynonym : function(contextMenu, contextMenuItem, mouseEvent) {
        var that = this;
        var targetNode = contextMenu.getTarget();
        var dto = this.termCache[this.__getRunwayIdFromNode(targetNode)];
        
        this.setNodeBusy(targetNode, true);
        
        new com.runwaysdk.ui.RunwayControllerFormDialog({
          type: "com.runwaysdk.system.gis.geo.Synonym",
          action: "delete",
          dto: dto,
          onSuccess : function(response) {
            that.getImpl().tree("removeNode", targetNode);
            that.setNodeBusy(targetNode, false);
          },
          onFailure : function(e) {
            that.setNodeBusy(targetNode, false);
            that.handleException(e);
          },
          onCancel : function() {
            that.setNodeBusy(targetNode, false);
          }
        }).render();
      },
      /**
       * Creates the "Synonyms" node and then loads the synonyms under it.
       */
      __onContextViewSynonymsClick : function(contextMenu, contextMenuItem, mouseEvent) {
        var targetNode = contextMenu.getTarget();
        var that = this;
        var runwayId = that.__getRunwayIdFromNode(targetNode);
        
        if (targetNode.data != null && targetNode.data.isSynonymContainer) { return; }
        
        var createSynonymNode = function(){
          targetNode.dontClobberChildren = true;
          
          var synId = targetNode.id + "_" + Mojo.Util.generateId();
          
          // Create a synonyms node under the target node
          var justCreated = that.__createTreeNode(synId, targetNode, true, {label: that.localize("synonymsNode"), data: {isSynonymContainer: true}});
          
          targetNode.data.synonymNode = justCreated;
          that.setNodeBusy(targetNode, false);
          
          // Load the synonyms
          that.refreshTerm(synId);
        };
        
        that.setNodeBusy(targetNode, true);
        
        if (!that.hasLoadedNode(targetNode)) {
          that.refreshTerm(runwayId, {
            onSuccess : function() {
              createSynonymNode();
            },
            onFailure : function() {
              that.setNodeBusy(targetNode, false);
            }
          });
        }
        else {
          createSynonymNode();
        }
      },
      
      // @Override
//      __findInsertIndex : function(label, newParent) {
//        var children = this.getChildren(newParent);
//        
//        children.sort(function(nodeA,nodeB){
//          if (newParent.data != null && nodeA === newParent.data.synonymNode) {
//            return -1;
//          }
//          else if (newParent.data != null && nodeB === newParent.data.synonymNode) {
//            return 1;
//          }
//          
//          return nodeA.text.localeCompare(nodeB.text);
//        });
//        
//        var i = 0;
//        if (newParent.data != null && newParent.data.synonymNode != null) {
//          i = 1;
//        }
//        
//        for (; i < children.length; ++i) {
//          if (children[i].text.localeCompare(label) > 0) {
//            break;
//          }
//        }
//        
//        return i;
//      },
      
      // @Override
//      _check_callback : function(operation, node, node_parent, node_position, more) {
//        if (operation === "move_node") {
//          // You can't drag synonymContainer nodes.
//          if (node.data != null && node.data.isSynonymContainer) {
//            return false;
//          }
//          // You can't drag synonyms, either
//          else if (node.data.isSynonym) {
//            return false;
//          }
//          // And you can't drag GeoEntities into synonyms
//          else if (node_parent.data.isSynonym || node_parent.data.isSynonymContainer) {
//            return false;
//          }
//        }
//        
//        return true;
//      },
      
      /**
       * Override
       * 
       * Adds a special case handler for synonyms.
       */
      refreshTerm : function(termId, callback) {
        var that = this;
        var id = termId;
        
        this.setTermBusy(termId, true);
        
        var nodes = that.__getNodesById(termId);
        var parent = nodes[0];
        
        // Special case for synonyms.
        if (parent.data != null && parent.data.isSynonymContainer) {
          var parentId = this.__getRunwayIdFromNode(parent.parent);
          
          var cr = new Mojo.ClientRequest({
            onSuccess: function(response){
              var json = Mojo.Util.getObject(response);
              var tnrs = com.runwaysdk.DTOUtil.convertToType(json.returnValue);
              
              // Remove existing children
              for (var iNode = 0; iNode < nodes.length; ++iNode) {
                var node = nodes[iNode];
                node.hasFetched = true;
                var children = node.children.slice(0,node.children.length); // slice is used here to avoid concurrent modification, screwing up the loop.
                for (var i=0; i < children.length; i++) {
                  $(that.getRawEl()).tree("removeNode", children[i]);
                }
              }
              
              // Create a node for every term we got from the server.
              for (var i = 0; i < tnrs.length; ++i) {
                var childId = tnrs[i].getTerm().getId();
                
                var parentRecord = {parentId: termId, relId: tnrs[i].getRelationshipId(), relType: tnrs[i].getRelationshipType()};
                that.parentRelationshipCache.put(childId, parentRecord);
                 
                that.termCache[childId] = tnrs[i].getTerm();
                
                for (var iNode = 0; iNode < nodes.length; ++iNode) {
                  var node = nodes[iNode];
                  that.__createTreeNode(childId, node, false, {data: {isSynonym: true}});
                }
              }
              
              that.setTermBusy(termId, false);
            },
            onFailure : function(ex) {
              jsTreeCallback.call(this, []);
              that.handleException(ex);
              return;
            }
          });
          
          Mojo.Util.invokeControllerAction("com.runwaysdk.system.gis.geo.Synonym", "getDirectDescendants", {parentId: parentId}, cr);
        }
        else {
          this.$refreshTerm(termId, callback);
        }
      },
      
      // @Override
      /**
       * This override prints the universal display label along with the geo entity display label.
       */
      _getTermDisplayLabel : function(term) {
        var displayLabel = this.$_getTermDisplayLabel(term);
        
        if (term.universalDisplayLabel != null && term.universalDisplayLabel !== displayLabel) {
          displayLabel = displayLabel + " [" + term.universalDisplayLabel + "]";
        }
        
        return displayLabel;
      },
      
      _makeGeoEntityFromView : function(view) {
        var term = new com.runwaysdk.system.gis.geo.GeoEntity();
        term.getDisplayLabel().setLocalizedValue(view.getGeoEntityDisplayLabel());
        term.id = view.getGeoEntityId();
        term.universalDisplayLabel = view.getUniversalDisplayLabel();
        term.canCreateChildren = view.getCanCreateChildren();
        term.newInstance = false;
        return term;
      },
      
      __responseToTerm : function(view) {
        return this._makeGeoEntityFromView(view);
      },
      
      __responseToTNR : function(geoEntView) {
        var term = this._makeGeoEntityFromView(geoEntView);
        var relId = geoEntView.getRelationshipId();
        var relType = geoEntView.getRelationshipType();
        
        return new com.runwaysdk.business.ontology.TermAndRel({
          term: term,
          relType: relType,
          relId: relId,
          dto_type: "com.runwaysdk.business.ontology.TermAndRel"
        });
      },
      
      _onClickExportAll : function() {
        this.exportTerm(this.rootTermId);
      },
      
//      createExportAllButton : function() {
//        var but = this.getFactory().newButton(this.localize("exportAll"), Mojo.Util.bind(this, this._onClickExportAll));
//        
//        but.addClassName("btn btn-primary");
//        but.setStyle("margin-bottom", "20px");
//        
//        this._wrapperDiv.appendChild(but);
//      },
      
      render : function(parent) {
        
//        this.createExportAllButton();
        
        this._wrapperDiv.render(parent);
        
        this.$render(this._wrapperDiv);
        
      }
    }
  });
  
  return geoentityTree;
  
})();