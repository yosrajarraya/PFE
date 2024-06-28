import React, { useEffect, useState, useRef } from "react";
import { TreeList, Selection, Column } from "devextreme-react/tree-list";
import { useDispatch, useSelector } from 'react-redux';
import Form, { GroupItem, SimpleItem, Label } from "devextreme-react/form";
import { SelectBox } from "devextreme-react/select-box";
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
  const module = useSelector(state => state.DelegationsReducer.module);
  const forms = useSelector(state => state.DelegationsReducer.forms);
  const listMenus = useSelector(state => state.DelegationsReducer.listMenus);

  useEffect(() => {
    let newDataTreeList = [];
    if (module !== null) {
      let moduleList = _.cloneDeep(module);
      moduleList.forEach((mod) => {
        if (mod.idModule !== undefined && mod.idModule !== null) {
          mod.idParent = null;
          mod.id = mod.idModule;
          mod.module = mod.idModule;
          mod.type = "module";
          newDataTreeList.push(mod);
          if (listMenus !== null) {
            let menuList = listMenus.filter((menu) => { menu.idModule = mod.idModule })
            if (menuList) {
              menuList.forEach((menu, index) => {
                menu.id = `${mod.idModule}_menu_${menu.idMenu}`;
                menu.type = "menu";
                menu.idParent = mod.idModule;
                newDataTreeList.push(menu);
                if (forms !== null) {
                  let buttonList = forms.filter((menu) => { menu.idMenu = menu.id })
                  if (buttonList) {
                    buttonList.forEach((button, btnIndex) => {
                      button.id = `${menu.idMenu}_button_${button.idButton}`;
                      button.type = "button";
                      button.idModule = mod.idModule;
                      button.idParent = menu.id;
                      newDataTreeList.push(button);
                    });
                  }
                }

              });
            }
          }

        }
      });

    }

    setData(newDataTreeList);
    // Pre-select items based on initial selections
    let preSelectedModules = props.initialModules;
    let preSelectedMenus = props.initialMenus || [];
    let preSelectedButtons = props.initialButtons || [];
    preSelectedMenus = preSelectedMenus.map(x => { x.id = `${x.idModule}_menu_${x.idMenu}`; x.type = "menu"; x.idParent = x.idModule; return x; });
    preSelectedMenus = preSelectedButtons.map(x => { x.id = `${x.idMenu}_button_${x.idButton}`; x.type = "button"; x.idParent = `${x.idModule}_menu_${x.idMenu}`; return x; });
    setSelectedModules(preSelectedModules);
    setSelectedMenus(preSelectedMenus);
    setSelectedButtons(preSelectedButtons);
    let preSelectedData;
    if (preSelectedModules != null) {
      preSelectedData = [preSelectedModules, ...preSelectedMenus, ...preSelectedButtons];
    } else {
      preSelectedData = [...preSelectedMenus, ...preSelectedButtons];
    }


    setSelectedDataKeys(preSelectedData.map(item => item.id));
    if (preSelectedModules) {
      setDataSource(data.filter(item => item.idModule === preSelectedModules.idModule || item.idParent === preSelectedModules.idModule));

    }
  }, [module, props.initialModules, props.initialMenus, props.initialButtons]);

  const handleModuleSelectionChange = (e) => {
    const selectedModuleId = e.value;
    const selectedModule = module.find(mod => mod.idModule === selectedModuleId);
    if (selectedModule) {
      setCurrentModule(selectedModule);
      const moduleData = data.filter(item => item.idModule === selectedModule.idModule || item.idParent === selectedModule.idModule);
      setDataSource(moduleData);
    }
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

  const renderModuleSelect = () => {
    const obj = {
      title: "Module",
      dataSource: module,
      displayValue: "designation",
      valueExpr: "idModule",
      onValueChanged: handleModuleSelectionChange,
      disabled: props.disabled,
    };

    return (
      <GroupItem colCount={1}>
        <SimpleItem>
          <Label text={obj.title} />
          <SelectBox
            dataSource={obj.dataSource}
            displayExpr={obj.displayValue}
            valueExpr={obj.valueExpr}
            onValueChanged={obj.onValueChanged}
            disabled={obj.disabled}
            placeholder="Select a module"
            defaultValue={selectedModules!=null ? selectedModules.idModule : null}
            searchEnabled
            searchExpr={obj.displayValue}
          />
        </SimpleItem>
      </GroupItem>
    );


  };
  return (
    <div>
      <Form>
        {renderModuleSelect()}


      </Form>

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
        <Column dataField="designation" caption="List" />
      </TreeList>
    </div>
  );
}

export default ListData;
