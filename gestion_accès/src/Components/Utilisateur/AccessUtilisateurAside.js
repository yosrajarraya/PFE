import React, { useRef, useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import Form, { GroupItem } from 'devextreme-react/form';
import notify from 'devextreme/ui/notify';
import {
  handleClose,
  handleOpenModalConfirmation,
  handleCloseModalConfirmation,
} from '../../Redux/Actions/Utilisateur/UtilisateurAccessAside';

import { Text_Template, HeaderAside } from '../../Helper/editorTemplates';
import ListData from '../ListData/ListData';
import { getUtilisateur } from '../../Redux/Actions/Utilisateur/UtilisateurAside';
import {
  getModule,
  getMenu,
  getButton,
  addNewUtilisateur,
  deleteUtilisateur,
  editeUtilisateur,
} from '../../Redux/Actions/Utilisateur/Utilisateur';

const AccessUtilisateurAside = () => {
  const dispatch = useDispatch();
  const [selectedModules, setSelectedModules] = useState([]);
  const [selectedMenus, setSelectedMenus] = useState([]);
  const [selectedButtons, setSelectedButtons] = useState([]);
  const messages = useSelector((state) => state.intl.messages);
  const intl = useSelector((state) => state.intl);
  const isOpenAccess = useSelector((state) => state.UtilisateurAccessAsideReducer.isOpenAccess);
  const modeAsideAccess = useSelector((state) => state.UtilisateurAccessAsideReducer.modeAsideAccess);
  const module = useSelector((state) => state.UtilisateursReducer.module);
  const button = useSelector((state) => state.UtilisateursReducer.button);
  const menu = useSelector((state) => state.UtilisateursReducer.menu);
  const selectedUtilisateur = useSelector((state) => state.UtilisateurAccessAsideReducer.selectedUtilisateur );
  const treeList = useRef(null);
  const allUtilisateurs = useSelector((state) => state.UtilisateurAsideReducer.allUtilisateurs);

  let objInitialisation = {
    username: selectedUtilisateur ? selectedUtilisateur.username : '',
    designation: selectedUtilisateur ? selectedUtilisateur.designation : '',
    active: selectedUtilisateur ? selectedUtilisateur.active : true,
    dateExpiration: selectedUtilisateur ? selectedUtilisateur.dateExpiration : '',
    expirePassword: selectedUtilisateur ? selectedUtilisateur.expirePassword : null,
    nbExpirationPassword: selectedUtilisateur ? selectedUtilisateur.nbExpirationPassword : '',
    nbJourExpiration: selectedUtilisateur ? selectedUtilisateur.nbJourExpiration : '',
    expireCompte: selectedUtilisateur ? selectedUtilisateur.expireCompte : null,
    groupUsers: selectedUtilisateur ? selectedUtilisateur.groupUsers : [],
    accessModuleUsers: selectedUtilisateur ? selectedUtilisateur.accessModuleUsers : [],
    accessMenuUsers: selectedUtilisateur ? selectedUtilisateur.accessMenuUsers : [],
    accessButtonUsers: selectedUtilisateur ? selectedUtilisateur.accessButtonUsers : [],
  };
  let groupesSelected = selectedUtilisateur ? selectedUtilisateur.groupUsers : [];
  let dxForm = useRef(null);
  let formObj = useRef(objInitialisation);
  if (selectedUtilisateur && modeAsideAccess === 'ACCESS') {
    formObj.current = _.cloneDeep(selectedUtilisateur);
  }

  useEffect(() => {
    if (allUtilisateurs && allUtilisateurs.length === 0) dispatch(getUtilisateur());
  });

  useEffect(() => {
    dispatch(getModule());
  }, []);

  useEffect(() => {
    dispatch(getButton());
  }, []);

  useEffect(() => {
    dispatch(getMenu());
  }, []);

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
      useSubmitBehavior: true,
    };
  };

  const validateForm = (e) => {
    let utilisateur = _.cloneDeep(formObj.current);
    let validationForm = e.validationGroup.validate().isValid;

    if (modeAsideAccess === 'ACCESS') {
      if (validationForm) {
        let data = {
          username: utilisateur.username,
          designation: utilisateur.designation,
          active: utilisateur.active,
          password: utilisateur.password,
          confirmPassword: utilisateur.confirmPassword,
          dateExpiration: utilisateur.dateExpiration,
          expirePassword: utilisateur.expirePassword,
          nbExpirationPassword: utilisateur.nbExpirationPassword,
          nbJourExpiration: utilisateur.nbJourExpiration,
          expireCompte: utilisateur.expireCompte,
          groupUsers: groupesSelected.map((groupes) => ({
            groupe: groupes.groupe,
            username: utilisateur.username,
          })),
          accessModuleUsers: selectedModules.map((module) => ({
            idModule: module.idModule,
            userId: utilisateur.username,
          })),
          accessMenuUsers: selectedMenus.map((menu) => ({
            idMenu: menu.idMenu,
            idUser: utilisateur.username,
            numDelegate: 1,
            visible: true,
          })),
          accessButtonUsers: selectedButtons.map((button) => ({
            visible: true,
            numDelegate: 1,
            idButton: button.idButton,
            idUser: utilisateur.username,
          })),
        };

        dxForm.current.instance.getEditor('submitAside').option('disabled', true);

        dispatch(editeUtilisateur(data))
          .then((response) => {
            confirmCloseAside(e);
            notify('Success', 'success', 1000);
          })
          .catch((error) => {
            console.error('Error posting group:', error);
            dxForm.current.instance.getEditor('submitAside').option('disabled', false);
          });
      }
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
      icon: 'fas fa-times',
      onClick: (e) => {
        if (modeAsideAccess === 'ACCESS') {
          showModalAlert(e, 'closeAside');
        } else {
          confirmCloseAside(e);
        }

        intl.loadGrid = true;
      },
    };
  };

  const showModalAlert = (e, actionToDoOnClick) => {
    let messageToShow =
      actionToDoOnClick === 'delete'
        ? messages.WantToDeleteAnyway
        : `${messages.confirmDialogTextPartOne} ${messages.confirmDialogTextPartTwo}`;
    const handleBtnConfirmerModalConfirmation = () => {
      dispatch(handleCloseModalConfirmation());
      if (actionToDoOnClick === 'delete') {
        dispatch(deleteUtilisateur(selectedUtilisateur.username))
          .then(() => {
            confirmCloseAside(e);
            notify('Success', 'success', 1000);
          })
          .catch((err) => {
            notify(err, 'error', 500);
          });
      } else {
        confirmCloseAside(e);
      }
    };
    const handleBtnCancelModalConfirmation = () => {
      dispatch(handleCloseModalConfirmation());
    };
    dispatch(
      handleOpenModalConfirmation(
        messageToShow,
        handleBtnCancelModalConfirmation,
        handleBtnConfirmerModalConfirmation
      )
    );
  };

  const confirmCloseAside = (e) => {
    clearForm(e);
    dispatch(handleClose());
  };

  const handleDataSelect = (selectedModules, selectedMenus, selectedButtons) => {
    setSelectedModules(selectedModules);
    setSelectedMenus(selectedMenus);
    setSelectedButtons(selectedButtons);
  };

  const RenderGroupe = () => {
    let obj = {
      title: messages.username,
      dataField: 'username',
      modeAside: modeAsideAccess,
      disabled: modeAsideAccess === 'ACCESS',
    };

    return <GroupItem>{Text_Template(obj)}</GroupItem>;
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
        <ListData ref={treeList} {...obj2} onDataSelect={handleDataSelect} />
      </div>
    );
  };

  return (
    <div>
      {isOpenAccess && modeAsideAccess !== '' && (
        <aside className={'openned'} style={{ overflow: 'auto' }}>
          <div
            className="aside-dialog"
            style={{
              width: '90%',
              display: 'table',
            }}
          >
            <Form
              ref={dxForm}
              key={'formAccessUtilisateur'}
              formData={formObj.current}
              onInitialized={onInitializedFormGlobal}
              colCount={1}
              style={{
                width: '85%',
                display: 'table-row',
              }}
            >
              {HeaderAside({
                modeAsideAccess: modeAsideAccess,
                btnValider: validateAccessButtonOption(),
                btnReset: resetAccessButtonOption(),
                messages: messages,
              })}
              <GroupItem>
                <GroupItem colCount={3}>{RenderGroupe()}</GroupItem>
                <GroupItem colCount={2}>{RenderList()}</GroupItem>
              </GroupItem>
            </Form>
          </div>
        </aside>
      )}
    </div>
  );
};

export default AccessUtilisateurAside;
