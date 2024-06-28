import React, { useEffect, useState } from "react";
import { TreeList, Selection, Column } from "devextreme-react/tree-list";
import List from "devextreme-react/list";
import "./List.css";
import _ from 'lodash';

function ListData(props) {
  const [data, setData] = useState([]);
  const [expandedKeys, setExpandedKeys] = useState([]);
  const [selectedModules, setSelectedModules] = useState([]);
  const [selectedMenus, setSelectedMenus] = useState([]);
  const [selectedButtons, setSelectedButtons] = useState([]);
  const [selectedUtilisateurs, setSelectedUtilisateurs] = useState([]);
  const [currentModule, setCurrentModule] = useState(props.module[0]);
  const [dataSource, setDataSource] = useState([]);
  const [selectedData, setSelectedData] = useState([]);
  const [selectedDataKeys, setSelectedDataKeys] = useState([]);

  useEffect(() => {
    let newDataTreeList = [];
    props.module.forEach((mod) => {
      if (mod.idModule !== undefined && mod.idModule !== null) {
        mod.idParent = null;
        mod.id = mod.idModule;
        mod.module = mod.idModule;
        mod.type = "module";
        newDataTreeList.push(mod);

        if (mod.menuList) {
          mod.menuList.forEach((menu, index) => {
            menu.id = `${mod.idModule}_menu_${index}`;
            menu.type = "menu";
            menu.idParent = mod.idModule;
            newDataTreeList.push(menu);

            if (menu.buttonList) {
              menu.buttonList.forEach((button, btnIndex) => {
                button.id = `${menu.id}_button_${btnIndex}`;
                button.type = "button";
                button.idModule = mod.idModule;
                button.idParent = menu.id;
                newDataTreeList.push(button);
              });
            }
          });
        }
      }
    });

    setData(newDataTreeList);

  }, [props.module]);

  const handleListSelectionChange = (e) => {
    const selectedModule = e.addedItems[0];
    props.onModuleSelect(selectedModule);
    setCurrentModule(selectedModule);
    const moduleData = data.filter(item => item.idModule === selectedModule.idModule);
    setDataSource(moduleData);
    setSelectedMenus([]);
  };

  const handletreeListSelectionChange = (e) => {
    const newSelectedRowsData = e.selectedRowsData;
    const newSelectedModules = newSelectedRowsData.filter(item => item.type === "module");
    const newSelectedMenus = newSelectedRowsData.filter(item => item.type === "menu");
    const newSelectedButtons = newSelectedRowsData.filter(item => item.type === "button");

    setSelectedModules(newSelectedModules.concat(selectedModules));
    setSelectedMenus(newSelectedMenus.concat(selectedMenus));
    setSelectedButtons(newSelectedButtons.concat(selectedButtons));
    setSelectedData(newSelectedRowsData.concat(selectedData));
    setSelectedDataKeys(selectedData.map((m) => m.id))
  };

  const handleUtilisateurSelectionChange = (e) => {
    setSelectedUtilisateurs(e.addedItems);
  };

  useEffect(() => {
    props.onDataSelect(selectedModules, selectedMenus, selectedButtons, selectedUtilisateurs);
  }, [selectedModules, selectedMenus, selectedButtons, selectedUtilisateurs, props.onDataSelect]);
  
  return (
    <div>
      <div className="left">
        <List
          selectionMode="multiple"
          dataSource={props.allUtilisateurs}
          searchEnabled={true}
          onSelectionChanged={handleUtilisateurSelectionChange}
          itemRender={renderListItemUtilisateur}
          elementAttr={{ class: "list" }}
        />
      </div>

      <div className="right">
        <TreeList
          id="module"
          dataSource={dataSource}
          showRowLines={true}
          showBorders={true}
          columnAutoWidth={true}
          autoExpandAll={true}
          defaultExpandedRowKeys={expandedKeys}
          defaultSelectedRowKeys={[...selectedDataKeys]}
         // onSelectionChanged={handletreeListSelectionChange}
          onSelectionChanged={handleListSelectionChange} 
          keyExpr="id"
          parentIdExpr="idParent"
        >
          <Selection recursive={false} mode="multiple" />
          <Column dataField="designation" caption="list" />
        </TreeList>
      </div>
    </div>
  );
}



function renderListItemUtilisateur(item) {
  return (
    <div>
      <div className="user">
        <div className="name">{item.username}</div>
      </div>
    </div>
  );
}

export default ListData;
