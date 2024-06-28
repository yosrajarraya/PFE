import React, { useEffect, useState } from "react";
import { TreeList, Selection, Column } from "devextreme-react/tree-list";
import List from "devextreme-react/list";
import "./List.css";
import _ from 'lodash';

function ListData(props) {
  const [data, setData] = useState([]);
  const [expandedKeys, setExpandedKeys] = useState([]);
  const [currentModule, setCurrentModule] = useState(null);
  const [dataSource, setDataSource] = useState([]);
  const [selectedDataKeys, setSelectedDataKeys] = useState([]);
  const [selectedModules, setSelectedModules] = useState([]);
  const [selectedMenus, setSelectedMenus] = useState([]);
  const [selectedButtons, setSelectedButtons] = useState([]);
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

    // Pre-select items based on initial selections
    const preSelectedModules = props.initialModules || [];
    const preSelectedMenus = props.initialMenus || [];
    const preSelectedButtons = props.initialButtons || [];

    setSelectedModules(preSelectedModules);
    setSelectedMenus(preSelectedMenus);
    setSelectedButtons(preSelectedButtons);

    const preSelectedData = [...preSelectedModules, ...preSelectedMenus, ...preSelectedButtons];
    setSelectedDataKeys(preSelectedData.map(item => item.id));
  }, [props.module, props.initialModules, props.initialMenus, props.initialButtons]);

  const handleListSelectionChange = (e) => {
    const selectedModule = e.addedItems[0];
    setCurrentModule(selectedModule);
    const moduleData = data.filter(item => item.idModule === selectedModule.idModule || item.idParent === selectedModule.idModule);
    setDataSource(moduleData);
  };

  const handletreeListSelectionChange = (e) => {
    const newSelectedRowsData = e.selectedRowsData;
    const newSelectedModules = newSelectedRowsData.filter(item => item.type === "module");
    const newSelectedMenus = newSelectedRowsData.filter(item => item.type === "menu");
    const newSelectedButtons = newSelectedRowsData.filter(item => item.type === "button");

    setSelectedModules([...new Set([...selectedModules, ...newSelectedModules])]);
    setSelectedMenus([...new Set([...selectedMenus, ...newSelectedMenus])]);
    setSelectedButtons([...new Set([...selectedButtons, ...newSelectedButtons])]);

    const allSelectedData = [...new Set([...selectedModules, ...selectedMenus, ...selectedButtons, ...newSelectedRowsData])];
    setSelectedDataKeys(allSelectedData.map(item => item.id));
  };

  useEffect(() => {
    props.onDataSelect(selectedModules, selectedMenus, selectedButtons);
  }, [selectedModules, selectedMenus, selectedButtons, props]);

  return (
    <div>
      <div className="left">
        <List
          selectionMode="single"
          dataSource={props.module}
          searchEnabled={true}
          onSelectionChanged={handleListSelectionChange}
          itemRender={renderListItem}
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
          selectedRowKeys={selectedDataKeys}
          onSelectionChanged={handletreeListSelectionChange}
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

function renderListItem(item) {
  return (
    <div>
      <div className="hotel">
        <div className="name">{item.designation}</div>
      </div>
    </div>
  );
}

export default ListData;
