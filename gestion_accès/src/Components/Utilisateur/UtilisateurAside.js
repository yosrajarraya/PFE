import React, { useEffect, useRef } from 'react';
import { useDispatch, useSelector } from "react-redux";
import _ from 'lodash';
import notify from "devextreme/ui/notify";
import Form, {
    GroupItem,

} from 'devextreme-react/form';
import "devextreme/dist/css/dx.light.css";

import { CheckBox } from 'devextreme-react/check-box';

import {
    Text_Template,
    HeaderAside,
    Date_Template,
    checkBox_Template,
    Password_Template,
    Number_Integer_Template
} from "../../Helper/editorTemplates";
import "react-datepicker/dist/react-datepicker.css";
import 'status-indicator/styles.css';
import {
    handleClose,
    handleOpenModalConfirmation,
    handleCloseModalConfirmation,
    getGroupe,
    getModules,
    getUtilisateur
} from "../../Redux/Actions/Utilisateur/UtilisateurAside";
import {
    addNewUtilisateur,
    editeUtilisateur,
    deleteUtilisateur,

} from "../../Redux/Actions/Utilisateur/Utilisateur";
import 'react-confirm-alert/src/react-confirm-alert.css';

import DataGrid, {
    Column,
    GroupPanel,
    Paging,
    SearchPanel,
    FilterRow,
    Selection
} from 'devextreme-react/data-grid';
const UtilisateurAside = () => {
    const dispatch = useDispatch();

    const messages = useSelector(state => state.intl.messages);
    const intl = useSelector(state => state.intl);

    const isOpen = useSelector(state => state.UtilisateurAsideReducer.isOpen);
    const modeAside = useSelector(state => state.UtilisateurAsideReducer.modeAside);

    const btnAddInstance = useSelector(state => state.UtilisateursReducer.btnAddInstance);
    const btnEditionInstance = useSelector(state => state.UtilisateursReducer.btnEditionInstance);

    const selectedUtilisateur = useSelector(state => state.UtilisateurAsideReducer.selectedUtilisateur);
    const allGroupes = useSelector(state => state.UtilisateurAsideReducer.allGroupes);
    const allModules = useSelector(state => state.UtilisateurAsideReducer.allModules);
    const allUtilisateurs = useSelector(state => state.GroupeAsideReducer.allUtilisateurs);

    const dataGridUtilisateurGroupes = useRef(null);
    const dataGridUtilisateurModules = useRef(null);


    const onSelectionChangedgrp = ({ selectedRowKeys, selectedRowsData }) => {
        groupesSelected = selectedRowsData;
    };

    const onSelectionChangedmod = ({ selectedRowKeys, selectedRowsData }) => {
        modulessSelected = selectedRowsData;
    };

    let objInitialisation = {
        username: selectedUtilisateur ? selectedUtilisateur.username : '',
        designation: selectedUtilisateur ? selectedUtilisateur.designation : '',
        active: selectedUtilisateur ? selectedUtilisateur.active : true,
        dateExpiration: selectedUtilisateur ? selectedUtilisateur.dateExpiration : '',
        expirePassword: selectedUtilisateur ? selectedUtilisateur.expirePassword : false,
        nbExpirationPassword: selectedUtilisateur ? selectedUtilisateur.nbExpirationPassword : '',
        nbJourExpiration: selectedUtilisateur ? selectedUtilisateur.nbJourExpiration : '',
        expireCompte: selectedUtilisateur ? selectedUtilisateur.expireCompte : false,
        groupUsers: selectedUtilisateur ? selectedUtilisateur.groupUsers : [],
        accessModuleUsers: selectedUtilisateur ? selectedUtilisateur.accessModuleUsers : [],
        accessMenuUsers: selectedUtilisateur ? selectedUtilisateur.accessMenuUsers : [],
        accessButtonUsers: selectedUtilisateur ? selectedUtilisateur.accessButtonUsers : [],
    
    };


    let groupesSelected = selectedUtilisateur ? selectedUtilisateur.groupUsers : [];
    let modulessSelected = selectedUtilisateur ? selectedUtilisateur.accessModuleUsers : [];
    let menusSelected = selectedUtilisateur ? selectedUtilisateur.menusSelected : [];
    let buttonsSelected = selectedUtilisateur ? selectedUtilisateur.buttonsSelected : [];

    let dxForm = useRef(null);
    let formObj = useRef(_.cloneDeep(objInitialisation));

    if (selectedUtilisateur && (modeAside === 'CONSULT' || modeAside === 'EDIT' || modeAside === 'VALIDATE' || modeAside === 'DELETE')) {
        formObj.current = _.cloneDeep(selectedUtilisateur);
    } else {
        formObj.current = _.cloneDeep(objInitialisation);
    }

    useEffect(() => {

        if (allGroupes && allGroupes.length === 0)
            dispatch(getGroupe())
    })
    useEffect(() => {

        if (allModules && allModules.length === 0)
            dispatch(getModules())
    })

    useEffect(() => {

        if (allUtilisateurs && allUtilisateurs.length === 0)
            dispatch(getUtilisateur())
    })




    const validateForm = (e) => {
        let utilisateur = _.cloneDeep(formObj.current);
        let validationForm = e.validationGroup.validate().isValid;
        let data = {};

        if (modeAside === 'ADD') {
            if (validationForm) {
                data = {
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
                    groupUsers: groupesSelected.map(groupes => ({
                        groupe: groupes.groupe,
                        username: utilisateur.username,

                    })),
                    accessModuleUsers: modulessSelected.map(module => ({
                        idModule: module.idModule,
                        userId: utilisateur.username,
                        idAccessModuleUser: null,
                        code: "code"
                    })),
                    accessMenuUsers:menusSelected.map(menu=>({
                        idMenu:menu.idMenu,
                        idUser:utilisateur.username,
                    })),
                    accessButtonUsers:buttonsSelected.map(button=>({
                        idButton:button.idButton,
                        idUser:utilisateur.username,
                    })),
                };

                dxForm.current.instance.getEditor('submitAside').option("disabled", true);

                dispatch(addNewUtilisateur(data))
                    .then(response => {

                        confirmCloseAside(e);
                        notify("Success", 'success', 1000);
                    })
                    .catch(error => {
                        console.error("Error posting group:", error);
                        dxForm.current.instance.getEditor('submitAside').option("disabled", false);
                    });
            }


        } else if (modeAside === 'EDIT') {
            if (validationForm) {
                data = {
                    username: utilisateur.username,
                    designation: utilisateur.designation,
                    active: utilisateur.active,
                    password: utilisateur.password,
                    dateExpiration: utilisateur.dateExpiration,
                    expirePassword: utilisateur.expirePassword,
                    nbExpirationPassword: utilisateur.nbExpirationPassword,
                    nbJourExpiration: utilisateur.nbJourExpiration,
                    expireCompte: utilisateur.expireCompte,
                    confirmPassword: utilisateur.confirmPassword, 
                    groupUsers: groupesSelected.map(groupes => ({
                        groupe: groupes.groupe,
                        username: utilisateur.username,

                    })),
                    accessModuleUsers: modulessSelected.map(module => ({
                        idModule: module.idModule,
                        userId: utilisateur.username,
                        idAccessModuleUser: null,

                    })),
                };


                dispatch(editeUtilisateur(data))
                    .then(response => {

                        confirmCloseAside(e);
                        notify("Success", 'success', 1000);
                    })

                    .catch(error => {
                        console.error("Error updating group:", error);
                        dxForm.current.instance.getEditor('submitAside').option("disabled", false);
                    });
            }
        } else if (modeAside === 'DELETE') {
            if (validationForm) {
                dispatch(deleteUtilisateur(selectedUtilisateur))
                    .then(() => {
                        confirmCloseAside(e);
                        notify("Success", 'success', 1000);
                    }).catch(err => {
                        notify(err, 'error', 500);
                    });
            }
        }
    };

    const onInitializedFormGlobal = (e) => {
        dxForm.current = e.component;
    }
    const validateButtonOption = () => {
        return {
            icon: 'fa fa-check',
            onClick: (e) => {
                intl.loadGrid = true;
                validateForm(e);
            },
            useSubmitBehavior: true
        }
    };
    const clearForm = (e) => {
        /*    if (modeAside === modeAsideEnum.editMode) {
                e.validationGroup.reset();
          }*/
        cleanObject();
    };

    const cleanObject = () => {
        formObj.current = _.cloneDeep(objInitialisation);
    };

    const resetButtonOption = () => {
        return {
            icon: "fas fa-times",//constants.iconReset
            onClick: (e) => {
                if (modeAside === 'ADD' || modeAside === 'EDIT' || modeAside === 'CONSULT') {
                    showModalAlert(e, 'closeAside');
                } else {
                    confirmCloseAside(e);
                }

                intl.loadGrid = true;
            }
        }
    };
    const showModalAlert = (e, actionToDoOnClick) => {
        let messageToShow = actionToDoOnClick === 'delete' ?
            messages.WantToDeleteAnyway
            : `${messages.confirmDialogTextPartOne} ${messages.confirmDialogTextPartTwo}`;
        const handleBtnConfirmerModalConfirmation = () => {
            dispatch(handleCloseModalConfirmation());
            if (actionToDoOnClick === 'delete') {
                dispatch(deleteUtilisateur(selectedUtilisateur.username))
                    .then(() => {
                        confirmCloseAside(e);
                        notify("Success", 'success', 1000);

                    }).catch(err => {
                        notify(err, 'error', 500);
                    });
            } else {
                confirmCloseAside(e);
            }
        }
        const handleBtnCancelModalConfirmation = () => {
            dispatch(handleCloseModalConfirmation());
        }
        dispatch(handleOpenModalConfirmation(messageToShow, handleBtnCancelModalConfirmation, handleBtnConfirmerModalConfirmation));
    };

    const confirmCloseAside = (e) => {
        clearForm(e);
        dispatch(handleClose());
        // btnAddInstance.option('disabled', false);
        // btnEditionInstance.option('disabled', false);
    };


    const Renderdesignation = () => {
        console.log("Renderdesignation")
        let obj = {
            title: "description",
            dataField: "designation",
            modeAside: modeAside,
            disabled: modeAside === 'EDIT' || modeAside === 'CONSULT' || modeAside === 'DELETE'

        }
        return (
            <GroupItem >
                {Text_Template(obj)}
            </GroupItem>
        )
    }
    const RenderMotsDePasse = () => {
        console.log("RenderMotsDePasse")
        let obj = {
            title: "mot de passe",
            dataField: "password",
            modeAside: modeAside,
            disabled:  modeAside === 'EDIT' || modeAside === 'CONSULT' || modeAside === 'DELETE'
        }
        return (
            <GroupItem >
                {Password_Template(obj)}
            </GroupItem>
        )
    }
    const RenderConfirmationMotsDePasse = () => {
        console.log("RenderConfirmationMotsDePasse")
        let obj = {
            title: " Confirmer le mot de passe",
            dataField: "confirmPassword",
            modeAside: modeAside,
            disabled: modeAside === 'EDIT' || modeAside === 'CONSULT' || modeAside === 'DELETE'
        }
        return (
            <GroupItem >
                {Password_Template(obj)}
            </GroupItem>
        )
    }

    const Renderactive = () => {
        console.log("Renderactive")
        let obj = {
            title: "active",
            dataField: "active",
            modeAside: modeAside,
            disabled: false
        }
        return (
            <GroupItem >
                {checkBox_Template(obj)}
            </GroupItem>
        )
    }
    const Renderusername = () => {
        console.log("Renderusername")
        let obj = {
            title: "nom d'utilisateur",
            dataField: "username",
            modeAside: modeAside,
            disabled: modeAside === 'EDIT' || modeAside === 'CONSULT' || modeAside === 'DELETE'
        }
        return (
            <GroupItem >
                {Text_Template(obj)}
            </GroupItem>
        )
    }




    const handleExpireCompteChange = (e) => {
        dxForm.current.instance.itemOption('blocExpireCompte.dateExpiration', 'visible', e.value);
        dxForm.current.instance.itemOption('blocExpireCompte.nbJourExpiration', 'visible', e.value);
        dxForm.current.instance.itemOption('blocExpireCompte.dateExpiration', 'isRequired', e.value);
        dxForm.current.instance.itemOption('blocExpireCompte.nbJourExpiration', 'isRequired', e.value);
        let editorOptionsDateExpiration = dxForm.current.instance.itemOption('blocExpireCompte.dateExpiration').editorOptions;
        editorOptionsDateExpiration.disabled = !e.value;
        dxForm.current.instance.itemOption('blocExpireCompte.dateExpiration', "editorOptions", editorOptionsDateExpiration);


        let editorOptionsNbJourExpiration = dxForm.current.instance.itemOption('blocExpireCompte.nbJourExpiration').editorOptions;
        editorOptionsNbJourExpiration.disabled = !e.value;
        dxForm.current.instance.itemOption('blocExpireCompte.nbJourExpiration', "editorOptions", editorOptionsNbJourExpiration);

        dxForm.current.instance.updateData('dateExpiration', null);
        dxForm.current.instance.updateData('nbJourExpiration', 0);
    };

    const handleExpirePasswordChange = (e) => {
        dxForm.current.instance.itemOption('blocExpirePassword.nbExpirationPassword', 'visible', e.value);
        dxForm.current.instance.itemOption('blocExpirePassword.nbExpirationPassword', 'isRequired', e.value);
        let editorOptionsNbJourExpiration = dxForm.current.instance.itemOption('blocExpirePassword.nbExpirationPassword').editorOptions;
        editorOptionsNbJourExpiration.disabled = !e.value;
        dxForm.current.instance.itemOption('blocExpirePassword.nbExpirationPassword', "editorOptions", editorOptionsNbJourExpiration);

        dxForm.current.instance.updateData('nbExpirationPassword', 0);
    };
    const RenderexpireCompte = () => {
        console.log("RenderexpireCompte")
        let obj = {
            title: "Expire Compte",
            dataField: "expireCompte",
            onValueChanged: handleExpireCompteChange,
            modeAside: modeAside,
            disabled: modeAside === "DELETE" || modeAside === "CONSULT"
        }
        return (
            <GroupItem >
                {checkBox_Template(obj)}
            </GroupItem>
        )
    };
    const RenderdateExpiration = () => {
        let obj = {
            title: "Date d'Expiration",
            dataField: "dateExpiration",
            modeAside: modeAside,
            disabled: true,
            visible:false,
            displayFormat: "yyyy-MM-dd"
        };

        return (
            <GroupItem style={{ display: 'none' }}>
                {Date_Template(obj)}
            </GroupItem>
        );
    };

    const RendernbJourExpiration = () => {
        let obj = {
            title: "Nombre de Jours d'expiration de compte",
            dataField: "nbJourExpiration",
            modeAside: modeAside,
            visible:false,
            isDisabled: true
        };

        return (
            <GroupItem>
                {Number_Integer_Template(obj)}
            </GroupItem>
        );
    };


    const RenderexpirePassword = () => {
        console.log("RenderexpirePassword")
        let obj = {
            title: "Expire mot de passe",
            dataField: "expirePassword",
            onValueChanged: handleExpirePasswordChange,
            modeAside: modeAside,
            disabled: modeAside === "DELETE" || modeAside === "CONSULT"
        }
        return (
            <GroupItem >
                {checkBox_Template(obj)}
            </GroupItem>
        )

    };



    const RendernbExpirationPassword = () => {
        let obj = {
            title: "Nombre de Jours d'expiration de Mot de Passe",
            dataField: "nbExpirationPassword",
            modeAside: modeAside,
            isDisabled: true,
            visible:false

        };
        return (
            <GroupItem>
                {Number_Integer_Template(obj)}
            </GroupItem>)
    };

    return (
        <div>
            {isOpen && modeAside === 'ADD' && (
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
                            key={'formCreateUtilisateur'}
                            formData={formObj.current}
                            onInitialized={onInitializedFormGlobal}
                            colCount={1}
                            style={{
                                width: "85%",
                                display: "table-row"
                            }}
                        >
                            {HeaderAside({
                                modeAside: modeAside,
                                btnValider: validateButtonOption(),
                                btnReset: resetButtonOption(),
                                messages: messages
                            })}
                            (
                            <GroupItem>

                                <GroupItem colCount={2}>
                                    {Renderusername()}
                                    {Renderdesignation()}

                                </GroupItem>
                                <GroupItem colCount={2}>
                                    {RenderMotsDePasse()}
                                    {RenderConfirmationMotsDePasse()}
                                </GroupItem >
                                <GroupItem name='blocExpireCompte' colCount={3}>
                                    {RenderexpireCompte()}
                                    {RendernbJourExpiration()}
                                    {RenderdateExpiration()}
                                </GroupItem>
                                <GroupItem name='blocExpirePassword' colCount={3}>
                                    {RenderexpirePassword()}
                                    {RendernbExpirationPassword()}
                                </GroupItem >
                                <GroupItem colCount={2}>

                                    <GroupItem name="listeSousSociete" caption="les groupes"  >

                                        <DataGrid
                                            ref={dataGridUtilisateurGroupes}
                                            onSelectionChanged={onSelectionChangedgrp}
                                            dataSource={allGroupes}
                                            allowColumnReordering={true}
                                            rowAlternationEnabled={true}
                                            showBorders={true}
                                            keyExpr="groupe"
                                            defaultSelectedRowKeys={formObj.current.groupUsers.map(y => { return y.groupe })}

                                        >
                                            <Paging defaultPageSize={4} />
                                            <GroupPanel visible={true} />
                                            <SearchPanel visible={true} highlightCaseSensitive={true} />
                                            <SearchPanel visible={true} placeholder={messages.search} />
                                            <FilterRow visible={true} />
                                            <Column
                                                dataField="groupe"
                                                caption="Groupe"
                                            />
                                            <Column
                                                dataField="designation"
                                                caption="Désignation" />
                                            <Selection
                                                mode="multiple"
                                            />
                                        </DataGrid>
                                    </GroupItem>
                                    {<GroupItem name="listeSousSociete" caption="les modules">
                                        <DataGrid
                                            ref={dataGridUtilisateurModules}
                                            onSelectionChanged={onSelectionChangedmod}
                                            dataSource={allModules}
                                            allowColumnReordering={true}
                                            rowAlternationEnabled={true}
                                            showBorders={true}
                                            keyExpr="idModule"
                                            defaultSelectedRowKeys={formObj.current.accessModuleUsers.map(y => { return y.idModule })}

                                        >
                                            <Paging defaultPageSize={4} />
                                            <GroupPanel visible={true} />
                                            <SearchPanel visible={true} highlightCaseSensitive={true} />
                                            <FilterRow visible={true} />

                                            
                                            <Column
                                                dataField="designation"
                                                caption="nom" />
                                            <Column
                                                dataField="active"
                                                caption="Active" />
                                            <Column
                                                dataField="versionDatabase"
                                                caption="version du base de donnée" />
                                            
                                            <Selection
                                                mode="multiple"
                                            />
                                        </DataGrid>
                                    </GroupItem>}
                                </GroupItem>
                            </GroupItem>
                            )

                        </Form>
                    </div>
                </aside>
            )} {isOpen && modeAside !== 'ADD' && (
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
                            key={'formCreateUtilisateur'}
                            formData={formObj.current}
                            onInitialized={onInitializedFormGlobal}
                            colCount={1}
                            style={{
                                width: "85%",
                                display: "table-row"
                            }}
                        >
                            {HeaderAside({
                                modeAside: modeAside,
                                btnValider: validateButtonOption(),
                                btnReset: resetButtonOption(),
                                messages: messages
                            })}
                            (
                            <GroupItem>

                                <GroupItem colCount={3}>
                                    {Renderusername()}
                                    {Renderdesignation()}
                                    {Renderactive()}

                                </GroupItem>
                                <GroupItem name='blocExpireCompte' colCount={3}>
                                    {RenderexpireCompte()}
                                    {RendernbJourExpiration()}
                                    {RenderdateExpiration()}
                                </GroupItem>
                                <GroupItem name='blocExpirePassword' colCount={3}>
                                    {RenderexpirePassword()}
                                    {RendernbExpirationPassword()}
                                </GroupItem >


                                <GroupItem colCount={2}>

                                    <GroupItem name="listeSousSociete" caption="les groupes"  >

                                        <DataGrid
                                            ref={dataGridUtilisateurGroupes}
                                            onSelectionChanged={onSelectionChangedgrp}
                                            dataSource={allGroupes}
                                            allowColumnReordering={true}
                                            rowAlternationEnabled={true}
                                            showBorders={true}
                                            keyExpr="groupe"
                                            defaultSelectedRowKeys={formObj.current.groupUsers.map(x => { return x.groupe })}

                                        >
                                            <Paging defaultPageSize={4} />
                                            <GroupPanel visible={true} />
                                            <SearchPanel visible={true} highlightCaseSensitive={true} />
                                            <SearchPanel visible={true} placeholder={messages.search} />
                                            <FilterRow visible={true} />
                                            <Column
                                                dataField="groupe"
                                                caption="Groupe"
                                            />
                                            <Column
                                                dataField="designation"
                                                caption="designation" />
                                            <Selection
                                                mode="multiple"
                                            />
                                        </DataGrid>
                                    </GroupItem>
                                    <GroupItem name="listeSousSociete" caption="les modules">
                                        <DataGrid
                                            ref={dataGridUtilisateurModules}
                                            onSelectionChanged={onSelectionChangedmod}
                                            dataSource={allModules}
                                            allowColumnReordering={true}
                                            rowAlternationEnabled={true}
                                            showBorders={true}
                                            keyExpr="idModule"
                                            //defaultSelectedRowKeys={formObj.current.accessModuleUsers.map(x => { return x.idModule })}
                                            defaultSelectedRowKeys={formObj.current.accessModuleUsers ? formObj.current.accessModuleUsers.map(x => x.idModule) : []}

                                        >
                                            <Paging defaultPageSize={4} />
                                            <GroupPanel visible={true} />
                                            <SearchPanel visible={true} highlightCaseSensitive={true} />
                                            <FilterRow visible={true} />

                                            
                                            <Column
                                                dataField="designation"
                                                caption="nom" />
                                            <Column
                                                dataField="active"
                                                caption="Active" />
                                            <Column
                                                dataField="versionDatabase"
                                                caption="version du base de donnée" />
                                            <Column
                                                dataField="urlWeb"
                                                caption="URL web" />
                                            <Selection
                                                mode="multiple"
                                            />
                                        </DataGrid>
                                    </GroupItem>
                                </GroupItem>
                            </GroupItem>
                            )

                        </Form>
                    </div>
                </aside>
            )}

        </div>

    );
}
export default UtilisateurAside