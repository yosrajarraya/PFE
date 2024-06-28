import React, { useRef, useEffect,useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import Form, { GroupItem } from 'devextreme-react/form';
import notify from "devextreme/ui/notify";
import _ from 'lodash';
import {
  handleClose,
  handleOpenModalConfirmation,
  handleCloseModalConfirmation,

} from "../../Redux/Actions/Groupe/GroupeAccessAside";
import {
  select_Template_new,
  HeaderAside,
  Text_Template
} from "../../Helper/editorTemplates";
import ListData from "../ListData/ListData";
import { getGroupe } from '../../Redux/Actions/Utilisateur/UtilisateurAside';
import { getModule, getMenu, getbutton, editeGroupe, deleteGroupe, addNewGroupe } from '../../Redux/Actions/Groupe/Groupe';

const AccessGroupeAside = () => {
  const dispatch = useDispatch();
  const messages = useSelector(state => state.intl.messages);
  const intl = useSelector(state => state.intl);
  const isOpenAccess = useSelector(state => state.GroupeAccessAsideReducer.isOpenAccess);
  const modeAsideAccess = useSelector(state => state.GroupeAccessAsideReducer.modeAsideAccess);
  const module = useSelector(state => state.GroupesReducer.module);
  const button = useSelector(state => state.GroupesReducer.button);
  const menu = useSelector(state => state.GroupesReducer.menu);
  const selectedGroupe = useSelector(state => state.GroupeAccessAsideReducer.selectedGroupe);
  const [selectedModules, setSelectedModules] = useState([]);
  const [selectedMenus, setSelectedMenus] = useState([]);
  const [selectedButtons, setSelectedButtons] = useState([]);
  const treeList = useRef(null);

  let objInitialisation = {
    groupe: selectedGroupe ? selectedGroupe.groupe : '',
    designation: selectedGroupe ? selectedGroupe.designation : '',
    active: selectedGroupe ? selectedGroupe.active : true,
    groupUsers: selectedGroupe ? selectedGroupe.groupUsers : [],
    accessModuleGrps: selectedGroupe ? selectedGroupe.accessModuleGrps : [],
    accessMenuGrpList: selectedGroupe ? selectedGroupe.accessMenuGrpList : [],
    accessButtonGrpList: selectedGroupe ? selectedGroupe.accessButtonGrpList : [],
  };

  let groupesSelected = selectedGroupe ? selectedGroupe.groupUsers : [];
  let dxForm = useRef(null);
  let formObj = useRef(objInitialisation);

  if (selectedGroupe && (modeAsideAccess === 'ACCESS')) {
    formObj.current = _.cloneDeep(selectedGroupe);
  }

  const allGroupes = useSelector(state => state.UtilisateurAsideReducer.allGroupes);

  useEffect(() => {
    if (allGroupes && allGroupes.length === 0) {
      dispatch(getGroupe());
    }
    dispatch(getModule());
    dispatch(getbutton());
    dispatch(getMenu());
  }, [dispatch, allGroupes]);

  const onInitializedFormGlobal = (e) => {
    dxForm.current = e.component;
  };

  const validateAccessButtonOption = () => {
    return {
      icon: 'fa fa-check',
      onClick: (e) => {
        intl.loadGrid = true;
        validateForm(e);
      },
      useSubmitBehavior: true
    };
  };

  const validateForm = (e) => {
    let group = _.cloneDeep(formObj.current);
    let validationForm = e.validationGroup.validate().isValid;

    if (modeAsideAccess === 'ACCESS' && validationForm) {
      let data = {
        groupe: group.groupe,
        designation: group.designation,
        active: group.active,
        groupUsers: groupesSelected.map(groupes => ({
          groupe: group.groupe,
          username: groupes.username,
        })),
        accessModuleGrps: selectedModules.map(modules => ({
          idGroupUser: group.groupe,
          idModule: modules.idModule
        })),
        accessMenuGrpList: selectedMenus.map(menus => ({
          idGroupUser: group.groupe,
          idMenu: menus.idMenu
        })),
        accessButtonGrpList: selectedButtons.map(buttons => ({
          idGroup: group.groupe,
          idButton: buttons.idButton
        }))
      };

      dispatch(editeGroupe(data))
        .then(response => {
          confirmCloseAside(e);
          notify("Success", 'success', 1000);
        })
        .catch(error => {
          console.error("Error posting group:", error);
          dxForm.current.instance.getEditor('submitAside').option("disabled", false);
        });
    }
  };

  const clearForm = (e) => {
    cleanObject();
  };

  const cleanObject = () => {
    formObj.current = _.cloneDeep(objInitialisation);
  };

  const resetAccessButtonOption = () => {
    return {
      icon: "fas fa-times",
      onClick: (e) => {
        if (modeAsideAccess === 'ACCESS') {
          showModalAlert(e, 'closeAside');
        } else {
          confirmCloseAside(e);
        }
        intl.loadGrid = true;
      }
    };
  };

  const showModalAlert = (e, actionToDoOnClick) => {
    let messageToShow = actionToDoOnClick === 'delete' ?
      messages.WantToDeleteAnyway
      : `${messages.confirmDialogTextPartOne} ${messages.confirmDialogTextPartTwo}`;

    const handleBtnConfirmerModalConfirmation = () => {
      dispatch(handleCloseModalConfirmation());
      if (actionToDoOnClick === 'delete') {
        dispatch(deleteGroupe(selectedGroupe.groupe))
          .then(() => {
            confirmCloseAside(e);
            notify("Success", 'success', 1000);
          }).catch(err => {
            notify(err, 'error', 500);
          });
      } else {
        confirmCloseAside(e);
      }
    };

    const handleBtnCancelModalConfirmation = () => {
      dispatch(handleCloseModalConfirmation());
    };

    dispatch(handleOpenModalConfirmation(messageToShow, handleBtnCancelModalConfirmation, handleBtnConfirmerModalConfirmation));
  };

  const confirmCloseAside = (e) => {
    clearForm(e);
    dispatch(handleClose());
  };

  const handleDataSelect = (selectedModules, selectedMenus, selectedButtons) => {
    console.log('Modules sélectionnés:', selectedModules);
    console.log('Menus sélectionnés:', selectedMenus);
    console.log('Boutons sélectionnés:', selectedButtons);
    setSelectedModules(selectedModules);
    setSelectedMenus(selectedMenus);
    setSelectedButtons(selectedButtons);
  };

  const RenderGroupe = () => {
    let obj = {
      title: messages.groupe,
      dataField: "groupe",
      modeAside: modeAsideAccess,
      disabled: modeAsideAccess === 'ACCESS'
    };

    return (
      <GroupItem>
        {Text_Template(obj)}
      </GroupItem>
    );
  };

  const RenderList = () => {
    let obj2 = {
      module: module,
      listMenus: menu,
      listbutton: button,
      disabled: false,
    };

    return (
      <div className="">
        <ListData
          ref={treeList}
          {...obj2}
          onDataSelect={handleDataSelect}
        />
      </div>
    );
  };

  return (
    <div>
      {isOpenAccess && modeAsideAccess !== '' && (
        <aside className={"openned"} style={{ overflow: "auto" }}>
          <div className="aside-dialog" style={{ width: "90%", display: "table" }}>
            <Form
              ref={dxForm}
              key={'formAccessGroupe'}
              formData={formObj.current}
              onInitialized={onInitializedFormGlobal}
              colCount={1}
              style={{ width: "85%", display: "table-row" }}
            >
              {HeaderAside({
                modeAsideAccess: modeAsideAccess,
                btnValider: validateAccessButtonOption(),
                btnReset: resetAccessButtonOption(),
                messages: messages
              })}
              <GroupItem>
                <GroupItem colCount={3}>
                  {RenderGroupe()}
                </GroupItem>
                <GroupItem colCount={2}>
                  {RenderList()}
                </GroupItem>
              </GroupItem>
            </Form>
          </div>
        </aside>
      )}
    </div>
  );
};

export default AccessGroupeAside;
