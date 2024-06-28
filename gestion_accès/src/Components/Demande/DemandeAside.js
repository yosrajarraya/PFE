import React, { useEffect, useRef, useState } from "react";
import { ReactReduxContext, useDispatch, useSelector } from "react-redux";
import notify from "devextreme/ui/notify";
import Form, { GroupItem, Label } from "devextreme-react/form";
import {
  Text_Template,

  Date_Template,
  HeaderAside
} from "../../Helper/editorTemplates";
import { SelectBox } from "devextreme-react/select-box";

import "react-datepicker/dist/react-datepicker.css";
import "status-indicator/styles.css";
import {
  handleClose,
  handleOpenModalConfirmation,
  handleCloseModalConfirmation,
  getGroup,
  fetchModulesForUser
} from "../../Redux/Actions/Demande/DemandeAside";
import {
  getModule,
  addNewDemande,
  editeDemande,
  deleteDemande,
  getAllForm,
  getAllMenu,
  getAllUtilisateur
} from "../../Redux/Actions/Demande/Demande";

import "react-confirm-alert/src/react-confirm-alert.css";

import ListData from "../Demande/ListData";

const DemandeAside = () => {
  const dispatch = useDispatch();
  const messages = useSelector((state) => state.intl.messages);
  const intl = useSelector((state) => state.intl);

  const isOpen = useSelector((state) => state.DemandeAsideReducer.isOpen);
  const modeAside = useSelector((state) => state.DemandeAsideReducer.modeAside);
  const module = useSelector((state) => state.DemandesReducer.module);
  const allUtilisateurs = useSelector(state => state.DemandesReducer.allUtilisateurs);
  const [detailsdelegationAccess,setdetailsdelegationAccess ] = useState([]);
  const [detailsdelegationAccessMenu,setdetailsdelegationAccessMenu ] = useState([]);
  const [detailsdelegationAccessButton,setdetailsdelegationAccessButton ] = useState([]);

  const [selectedModules, setSelectedModules] = useState([]);
  const [selectedMenus, setSelectedMenus] = useState([]);
  const [selectedButtons, setSelectedButtons] = useState([]);
  const [selectedUtilisateurs, setSelectedUtilisateurs] = useState([]);
  const treeList = useRef(null);
  useEffect(() => {
    dispatch(getModule());
    dispatch(getAllForm());
    dispatch(getAllMenu());


  }, []);

  useEffect(() => {
    dispatch(getAllUtilisateur());
  }, [dispatch]);
  useEffect(() => {
    dispatch(getModule());
  }, [dispatch]);

  const selectedDemande = useSelector(
    (state) => state.DemandeAsideReducer.selectedDemande
  );


  let objInitialisation = {
    numDelegation: selectedDemande ? selectedDemande.numDelegation : "",
    userDelegant: selectedDemande ? selectedDemande.userDelegant : "",
    motif: selectedDemande ? selectedDemande.motif : "",
    module: selectedDemande ? selectedDemande.module : "",
    dateDebut: selectedDemande ? selectedDemande.dateDebut : "",
    dateFin: selectedDemande ? selectedDemande.dateFin : "",
    declenche: selectedDemande ? selectedDemande.declenche : false,
    termine: selectedDemande ? selectedDemande.termine : false,
    detailsdelegationAccess: selectedDemande ? selectedDemande.detailsdelegationAccess : [],
    detailsdelegationAccessMenu: selectedDemande ? selectedDemande.detailsdelegationAccessMenu : [],
    detailsdelegationAccessButton: selectedDemande ? selectedDemande.detailsdelegationAccessButton : []
  };
  let dxForm = useRef(null);
  let formObj = useRef(objInitialisation);

  if (selectedDemande && (modeAside === "CONSULT" || modeAside === "EDIT" || modeAside === "VALIDATE" || modeAside === "DELETE")
  ) { formObj.current = _.cloneDeep(selectedDemande); }

  const validateForm = (e) => {
    let demande = _.cloneDeep(formObj.current);
    let validationForm = e.validationGroup.validate().isValid;
    let data = {};
    if (modeAside === "ADD") {
      if (validationForm) {
        data = {
        
          userDelegant: demande.userDelegant,
          motif: demande.motif,
          dateDebut: demande.dateDebut,
          module: demande.module,
          dateFin: demande.dateFin,
          detailsdelegationAccess: detailsdelegationAccess.map(access=>({
            id_delegation:0,
            numDelegation: access.numDelegation,
            username:access.username
          })),
          detailsdelegationAccessMenu:detailsdelegationAccessMenu.map(accessMenu=>({
            id_delegation_menu:1,
            numDelegation:accessMenu.numDelegation,
            usernames:accessMenu.usernames,
            menus:accessMenu.menus,
            module:accessMenu.module
          })),
          detailsdelegationAccessButton: detailsdelegationAccessButton.map(accessbutton =>({
            id_delegation_button:2,
            numDelegation:accessbutton.numDelegation,
            usernames:accessbutton.usernames,
            buttons:accessbutton.buttons,
            module:accessbutton.module
          }))

        };
        dispatch(addNewDemande(data))
          .then(response => {

            confirmCloseAside(e);
            notify("Success", 'success', 1000);
          })
          .catch(error => {
            console.error("Error posting delegation:", error);
            dxForm.current.instance.getEditor('submitAside').option("disabled", false);
          });
      }
    } else if (modeAside === "EDIT") {
      if (validationForm) {
        data = {
          numDelegation: demande.numDelegation,
          userDelegant: demande.userDelegant,
          motif: demande.motif,
          dateDebut: demande.dateDebut,
          module: demande.module,
          dateFin: demande.dateFin,
          declenche: demande.declenche,
          termine: demande.termine,
          detailsdelegationAccess: demande.detailsdelegationAccess,
          detailsdelegationAccessMenu: demande.detailsdelegationAccessMenu,
          detailsdelegationAccessButton: demande.detailsdelegationAccessButton,
        }
        dispatch(editeDemande(data))
          .then(() => {
            confirmCloseAside(e);
            notify("Success", "success", 1000);
          })
          .catch((err) => {
            notify(err, "error", 500);
          });
      }
    } else if (modeAside === "DELETE") {
      dispatch(deleteDemande(data))
        .then(() => {
          confirmCloseAside(e);
          notify("Sucess", "success", 1000);
        })
        .catch((err) => {
          notify("err", "error", 500);
        });
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

    cleanObject();
  };

  const cleanObject = () => {
    formObj.current = _.cloneDeep(objInitialisation);
  };

  const resetButtonOption = () => {
    return {
      icon: "fas fa-times",
      onClick: (e) => {
        if (modeAside === "ADD" || modeAside === "EDIT" || modeAside === "CONSULT"
        ) {
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
        dispatch(deleteDemande(selectedDemande.numDelegation))
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
    // btnAddInstance.option("disabled", false);
    // btnEditionInstance.option("disabled", false);
  };
  const handleDataSelect = (selectedModules, selectedMenus, selectedButtons, selectedUtilisateurs) => {
    setSelectedModules(selectedModules);
    setSelectedMenus(selectedMenus);
    setSelectedButtons(selectedButtons);
    setSelectedUtilisateurs(selectedUtilisateurs);
  };

  const handleModuleSelectionChange = (selectedModule) => {
    console.log("Selected Module:", selectedModule);
    setCurrentModule(selectedModule);
    const moduleData = data.filter(item => item.idModule === selectedModule.idModule);
    setDataSource(moduleData);
    setSelectedMenus([]);
    setSelectedButtons([]);
    setSelectedUtilisateurs([]);
  };
  
  const RenderList = () => {
    let obj2 = {
      module: module,
      allUtilisateurs: allUtilisateurs,
      disabled: false,
    };
    return (
      <div className="">
        <ListData ref={treeList} {...obj2}
          onDataSelect={handleDataSelect}
          onModuleSelect={handleModuleSelectionChange}
        />
      </div>
    );
  };
  

  const RenderCodeDemande = () => {
    console.log("RenderCodeDemande");
    let obj = {
      title: messages.codeDemande,
      dataField: "codeDemande",
      colspan: 1,
      modeAside: modeAside,
      disabled: false
    };
    return <GroupItem>{Text_Template(obj)}</GroupItem>;
  };
  const RenderuserDelegant = () => {
    let obj = {
      title: "user Delegant",
      dataField: "userDelegant",
      modeAside: modeAside,

      handleChangeSelect: (e) => handleChangeUserDelegant(e.value),

    };

    return (
      <GroupItem>
        <Label text={obj.title} />

        <SelectBox
          dataSource={allUtilisateurs}
          displayExpr="username"
          valueExpr="username"
        />
      </GroupItem>
    );
  };
  const RenderModule = () => {
    let obj = {
      title: "Modules",
      dataField: "module",
      modeAside: modeAside,
      disabled: modeAside === "ADD"
    };

    return (
      <GroupItem>
        <Label text={obj.title} />
        <SelectBox
          dataSource={module}
          displayExpr="designation"
          valueExpr="idModule"
        />

      </GroupItem>
    );
  }; console.log("module", module)
  const RenderdateDebut = () => {
    let obj = {
      title: "Date debut",
      dataField: "dateDebut",
      modeAside: modeAside,

      displayFormat: "yyyy-MM-dd"
    };

    return (
      <GroupItem>
        {Date_Template(obj)}
      </GroupItem>
    );
  };
  const RenderdateFin = () => {
    let obj = {
      title: "Date fin",
      dataField: "dateFin",
      modeAside: modeAside,

      displayFormat: "yyyy-MM-dd"
    };

    return (
      <GroupItem >
        {Date_Template(obj)}
      </GroupItem>
    );
  };
  const RenderMotif = () => {
    const motifOptions = ["VACATION", "SICK_LEAVE", "BUSINESS_TRIP", "OTHER"];

    return (
      <GroupItem>
        <label htmlFor="motif">Motif:</label>
        <select id="motif">
          {motifOptions.map(option => (
            <option key={option} value={option}>{option}</option>
          ))}
        </select>
      </GroupItem>
    );
  };
  return (
    <div>
      {isOpen && modeAside !== "" && (
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
              key={"formCreateDemande"}
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
                  {RenderCodeDemande()}
                  {RenderuserDelegant()}
                  {RenderMotif()}
                </GroupItem>
                <GroupItem colCount={2}>
                  {RenderdateDebut()}
                  {RenderdateFin()}
                </GroupItem>
                <GroupItem colCount={1}>
                  {RenderModule()}

                </GroupItem>
                <GroupItem colCount={2}>
                  {RenderList()}
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
export default DemandeAside;