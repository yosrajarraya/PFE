import React, { useEffect, useRef, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import notify from "devextreme/ui/notify";
import Form, { GroupItem, Label } from "devextreme-react/form";
import _ from 'lodash';
import {
  Text_Template,
  Date_Template,
  select_Template_new,
  HeaderAside
} from "../../Helper/editorTemplates";
import DataGrid, {
  Column,
  GroupPanel,
  Paging,
  FilterRow,
  SearchPanel,
  Selection
} from 'devextreme-react/data-grid';
import { SelectBox } from "devextreme-react/select-box";
import "react-datepicker/dist/react-datepicker.css";
import "status-indicator/styles.css";
import {
  handleClose,
  handleOpenModalConfirmation,
  handleCloseModalConfirmation,
  getMotifs,
  fetchModulesForUser
} from "../../Redux/Actions/Delegation/DelegationAside";
import {
  getModule,
  addNewDelegation,
  editeDelegation,
  deleteDelegation,
  getAllForm,
  getAllMenu,
  getAllUtilisateur
} from "../../Redux/Actions/Delegation/Delegation";
import "react-confirm-alert/src/react-confirm-alert.css";
import ListData from "../Delegation/ListData";

const DelegationAside = () => {
  const dispatch = useDispatch();
  const messages = useSelector((state) => state.intl.messages);
  const intl = useSelector((state) => state.intl);
  const isOpen = useSelector((state) => state.DelegationAsideReducer.isOpen);
  const modeAside = useSelector((state) => state.DelegationAsideReducer.modeAside);
  const module = useSelector(state => state.DelegationsReducer.module);

  const button = useSelector(state => state.DelegationsReducer.button);
  const menu = useSelector(state => state.DelegationsReducer.menu);
  const allUtilisateurs = useSelector(state => state.DelegationsReducer.allUtilisateurs);
  const allMotif = useSelector(state => state.DelegationAsideReducer.allMotif);
  const [selectedModules, setSelectedModules] = useState([]);
  const [selectedMenus, setSelectedMenus] = useState([]);
  const [selectedButtons, setSelectedButtons] = useState([]);
  const filteredModules = useSelector((state) => state.DelegationsReducer.filteredModules);
  const dataGridGroupe = useRef(null);


  const selectedDelegation = useSelector((state) => state.DelegationAsideReducer.selectedDelegation);

  let objInitialisation = {
    numDelegation: selectedDelegation ? selectedDelegation.numDelegation : "",
    userDelegant: selectedDelegation ? selectedDelegation.userDelegant : "",
    motif: selectedDelegation ? selectedDelegation.motif : "",
    motifDTO: selectedDelegation ? selectedDelegation.motifDTO : null,
    module: selectedDelegation ? selectedDelegation.module : "",
    idModule: selectedDelegation ? selectedDelegation.idModule : null,
    utilisateurDTO: selectedDelegation ? selectedDelegation.utilisateurDTO : null,
    dateDebut: selectedDelegation ? selectedDelegation.dateDebut : null,
    dateFin: selectedDelegation ? selectedDelegation.dateFin : null,
    declenche: selectedDelegation ? selectedDelegation.declenche : false,
    termine: selectedDelegation ? selectedDelegation.termine : false,
    detailsdelegationAccess: selectedDelegation ? selectedDelegation.detailsdelegationAccess : [],
    detailsdelegationAccessMenu: selectedDelegation ? selectedDelegation.detailsdelegationAccessMenu : [],
    detailsdelegationAccessButton: selectedDelegation ? selectedDelegation.detailsdelegationAccessButton : []
  };
  let dxForm = useRef(null);
  let formObj = useRef(objInitialisation);
  formObj.current = objInitialisation;
  let objInitialisationCloned = _.cloneDeep(objInitialisation);

  useEffect(() => {
    if (allMotif && allMotif.length === 0)
      dispatch(getMotifs())
  },[dispatch])
  useEffect(() => {
    dispatch(getAllUtilisateur());
  }, [dispatch]);
  // if (selectedDelegation) {
  //   if (module === null) {
  //     dispatch(getModule(selectedDelegation.userDelegant));
  //     dispatch(getAllForm(selectedDelegation.userDelegant));
  //     dispatch(getAllMenu(selectedDelegation.userDelegant));
  //   }

  // }



  const validateForm = (e) => {
    let delegation = _.cloneDeep(formObj.current);
    let validationForm = e.validationGroup.validate().isValid;
    let data = {};

    if (validationForm) {
      data = {

        userDelegant: delegation.userDelegant,
        motif: delegation.motifDTO.code,
        dateDebut: delegation.dateDebut,
        dateFin: delegation.dateFin,
        termine: delegation.termine,
        declenche: delegation.declenche,
        detailsdelegationAccess: selectedUtilisateurs.map(user => ({
          userDelegataire: user.username,
          userDelegant: delegation.userDelegant
        })),
        detailsdelegationAccessMenu: selectedMenus.map(menus => ({
          module: menus.idModule,
          menu: menus.idMenu,
          userDelegant: delegation.userDelegant
        })),
        detailsdelegationAccessButton: selectedButtons.map(buttons => ({
          module: buttons.idModule,
          button: buttons.idButton,
          userDelegant: delegation.userDelegant
        }))
      };
      console.log('Data to be sent:', data);

      if (modeAside === "ADD") {
        dispatch(addNewDelegation(data))
          .then(() => {
            confirmCloseAside(e);
            notify("Success", "success", 1000);
          })
          .catch((err) => {
            console.error('Error posting delegation:', err);
            notify(err.message, "error", 500);
            dxForm.current.instance.getEditor('submitAside').option("disabled", false);
          });
      } else if (modeAside === "EDIT") {
        data = {
          numDelegation: delegation.numDelegation,
          userDelegant: delegation.userDelegant,
          motif: delegation.motifDTO.code,
          dateDebut: delegation.dateDebut,
          dateFin: delegation.dateFin,
          termine: delegation.termine,
          declenche: delegation.declenche,
          detailsdelegationAccess: selectedUtilisateurs.map(user => ({
            userDelegataire: user.username,
            userDelegant: delegation.userDelegant
          })),
          detailsdelegationAccessMenu: selectedMenus.map(menus => ({
            module: menus.idModule,
            menu: menus.idMenu,
            userDelegant: delegation.userDelegant
          })),
          detailsdelegationAccessButton: selectedButtons.map(buttons => ({
            module: buttons.idModule,
            button: buttons.idButton,
            userDelegant: delegation.userDelegant
          }))
        };
        dispatch(editeDelegation(data))
          .then(response => {
            confirmCloseAside(e);
            notify("Success", 'success', 1000);
          })
          .catch(error => {
            console.error("Error posting group:", error);
            dxForm.current.instance.getEditor('submitAside').option("disabled", false);
          });
      } else if (modeAside === "DELETE") {
        dispatch(deleteDelegation(selectedDelegation))
          .then(response => {
            confirmCloseAside(e);
            notify("Success", 'success', 1000);
          })
          .catch(error => {
            console.error("Error posting group:", error);
            dxForm.current.instance.getEditor('submitAside').option("disabled", false);
          });
      }
    }
  };



  const onInitializedFormGlobal = (e) => {
    dxForm.current = e.component;
  };

  const validateButtonOption = () => {
    return {
      icon: "fa fa-check",
      onClick: (e) => {
        intl.loadGrid = true;
        validateForm(e);
      },
      useSubmitBehavior: true,
    };
  };

  const clearForm = (e) => {
    if (modeAside === 'EDIT') {
      e.validationGroup.reset();
    }
    cleanObject();
  };

  const cleanObject = () => {
    formObj.current = objInitialisationCloned;
  };

  const resetButtonOption = () => {
    return {
      icon: "fas fa-times",
      onClick: (e) => {
        if (modeAside === "ADD" || modeAside === "EDIT" || modeAside === "CONSULT") {
          showModalAlert(e, "closeAside");
        } else {
          confirmCloseAside(e);
        }

        intl.loadGrid = true;
      },
    };
  };

  const showModalAlert = (e, actionToDoOnClick) => {
    let messageToShow =
      actionToDoOnClick === "delete"
        ? messages.WantToDeleteAnyway
        : `${messages.confirmDialogTextPartOne} ${messages.confirmDialogTextPartTwo}`;
    const handleBtnConfirmerModalConfirmation = () => {
      dispatch(handleCloseModalConfirmation());
      if (actionToDoOnClick === "delete") {
        dispatch(deleteDelegation(selectedDelegation.numDelegation))
          .then(() => {
            confirmCloseAside(e);
            notify("Success", "success", 1000);
          })
          .catch((err) => {
            notify(err, "error", 500);
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
    // setSelectedModules(selectedModules);
    // setSelectedMenus(selectedMenus);
    // setSelectedButtons(selectedButtons);
  };

  const RenderMotif = () => {
    let obj = {
      title: "Motif",
      dataSource: allMotif,
      dataField: "motifDTO",
      displayValue: "designation",
      valueExpr: "code",
      colspan: 1,
      modeAside: modeAside,
      messages: { Selection: "" },
      disabled: modeAside === "DELETE" || modeAside === "CONSULT"
    };


    return <GroupItem>{select_Template_new(obj)}</GroupItem>;

  };

  const RenderList = () => {
    let obj2 = {
      module: module,
      listMenus: menu,
      listbutton: button,
      disabled: modeAside === "DELETE" || modeAside === "CONSULT"
    };

    return (
      <div className="">
        <ListData
          dataField='access'
          // ref={treeList}
          {...obj2}
          modules={filteredModules}
          onDataSelect={handleDataSelect}
          initialModules={formObj.current.idModule}
          initialMenus={formObj.current.detailsdelegationAccessMenu.map(menu => { return menu.menu })}
          initialButtons={formObj.current.detailsdelegationAccessButton.map(button => { return button.idButton })}
          disabled={modeAside === "DELETE" || modeAside === "CONSULT"}
        />
      </div>
    );
  };


  const RenderCodeDelegation = () => {
    let obj = {
      title: "num delegation",
      dataField: "numDelegation",
      colspan: 1,
      modeAside: modeAside,
      disabled: modeAside === "ADD" || modeAside === "DELETE" || modeAside === "CONSULT" || modeAside === "EDIT",
      visible: modeAside !== "ADD"
    };
    return <GroupItem>{Text_Template(obj)}</GroupItem>;
  };
  const handleUserDelegantChange = (e) => {
    formObj.current.userDelegant = e.value.username;
    if (e.value) {
      dispatch(getModule(e.value.username));
      dispatch(getAllForm(e.value.username));
      dispatch(getAllMenu(e.value.username));
    }
  };


  const RenderUserDelegant = () => {
    let obj = {
      title: "Delegant",
      dataSource: allUtilisateurs,
      dataField: "utilisateurDTO",
      displayValue: "username",
      valueExpr: "username",
      colspan: 1,
      modeAside: modeAside,
      messages: { Selection: "" },
      handleChangeSelect: handleUserDelegantChange,
      disabled: modeAside === "DELETE" || modeAside === "CONSULT"
    };


    return <GroupItem>{select_Template_new(obj)}</GroupItem>;


  };

  const RenderDateDebut = () => {
    let obj = {
      title: "Date debut",
      dataField: "dateDebut",
      modeAside: modeAside,
      displayFormat: "yyyy-MM-dd",
      disabled: modeAside === "DELETE" || modeAside === "CONSULT"

    };

    return (
      <GroupItem>
        {Date_Template(obj)}
      </GroupItem>
    );
  };

  const RenderDateFin = () => {
    let obj = {
      title: "Date fin",
      dataField: "dateFin",
      modeAside: modeAside,
      displayFormat: "yyyy-MM-dd",
      disabled: modeAside === "DELETE" || modeAside === "CONSULT"

    };

    return (
      <GroupItem>
        {Date_Template(obj)}
      </GroupItem>
    );
  };


  const RenderListUser = () => {

    return (
      <DataGrid
        ref={dataGridGroupe}
        dataSource={allUtilisateurs}
        keyExpr="username"
        columnAutoWidth={true}
        showBorders={true}
        defaultSelectedRowKeys={formObj.current.detailsdelegationAccess.map(x => { return x.userDelegataire })}
      >  <FilterRow visible={true} />
        <GroupPanel visible={true} />

        <SearchPanel visible={true} highlightCaseSensitive={true} />
        <Selection mode="multiple" />
        <Column dataField="username" caption="Username" />
        <Column dataField="designation" caption="designation" />
      </DataGrid>
    );
  };

  return (
    <div>
      {isOpen && modeAside !== '' && (
        <aside className={"openned"} style={{ overflow: "auto" }}>
          <div
            className="aside-dialog"
            style={{
              width: "90%",
              display: "table",
            }}
          >
            <Form
              ref={dxForm}
              key={"formCreateDelegation"}
              formData={formObj.current}
              onInitialized={onInitializedFormGlobal}
              colCount={1}
              style={{
                width: "90%",
                display: "table-row",
              }}
            >
              {HeaderAside({
                modeAside: modeAside,
                btnValider: validateButtonOption(),
                btnReset: resetButtonOption(),
                messages: messages,
              })}
              (
              <GroupItem>
                <GroupItem colCount={3}>
                  {RenderCodeDelegation()}
                  {RenderUserDelegant()}
                  {RenderMotif()}
                </GroupItem>
                <GroupItem colCount={3}>
                  {RenderDateDebut()}
                  {RenderDateFin()}

                </GroupItem>
                <GroupItem colCount={2}>
                  <div className="list-data-container">
                    <div className="left">
                      {RenderListUser()}
                    </div>

                    <div className="right">
                      {RenderList()}
                    </div>
                  </div>

                </GroupItem>
              </GroupItem>
              )
            </Form>
          </div>
        </aside>
      )}
    </div>
  );
};
export default DelegationAside;
