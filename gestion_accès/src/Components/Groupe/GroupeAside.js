import React, { useEffect, useRef } from 'react';
import { ReactReduxContext, useDispatch, useSelector } from "react-redux";
import _ from 'lodash';
import notify from "devextreme/ui/notify";
import Form, {
    GroupItem,
    Label,
    SimpleItem
} from 'devextreme-react/form';
import {
    Text_Template,
    HeaderAside,
    checkBox_Template

} from "../../Helper/editorTemplates";
import "react-datepicker/dist/react-datepicker.css";
import 'status-indicator/styles.css';
import {
    handleClose,
    handleOpenModalConfirmation,
    handleCloseModalConfirmation,
    getUtilisateur
} from "../../Redux/Actions/Groupe/GroupeAside";
import {
    addNewGroupe,
    editeGroupe,
    deleteGroupe,
    getModule,
    getbutton,
    getMenu
} from "../../Redux/Actions/Groupe/Groupe";

import 'react-confirm-alert/src/react-confirm-alert.css';
import axios from 'axios';
import DataGrid, {
    Column,
    GroupPanel,
    Paging,
    FilterRow,
    SearchPanel,
    Selection
} from 'devextreme-react/data-grid';


const GroupeAside = () => {

    const dispatch = useDispatch();

    const messages = useSelector(state => state.intl.messages);
    const intl = useSelector(state => state.intl);

    const isOpen = useSelector(state => state.GroupeAsideReducer.isOpen);
    const modeAside = useSelector(state => state.GroupeAsideReducer.modeAside);

    const btnAddInstance = useSelector(state => state.GroupesReducer.btnAddInstance);
    const btnEditionInstance = useSelector(state => state.GroupesReducer.btnEditionInstance);

    const selectedGroupe = useSelector(state => state.GroupeAsideReducer.selectedGroupe);
    const allUtilisateurs = useSelector(state => state.GroupeAsideReducer.allUtilisateurs);


    const dataGridGroupe = useRef(null);

    const onSelectionChanged = ({ selectedRowKeys, selectedRowsData }) => {
        groupesSelected = selectedRowsData;
    };
    let objInitialisation = {

        groupe: selectedGroupe ? selectedGroupe.groupe : '',
        designation: selectedGroupe ? selectedGroupe.designation : '',
        active: selectedGroupe ? selectedGroupe.active : true,
        groupUsers: selectedGroupe ? selectedGroupe.groupUsers : [],
        accessMenuGrpList: selectedGroupe ? selectedGroupe.accessMenuGrpList : [],
        accessButtonGrpList: selectedGroupe ? selectedGroupe.accessButtonGrpList : [],
        accessModuleGrps: selectedGroupe ? selectedGroupe.accessModuleGrps : [],


    };
    let groupesSelected = selectedGroupe ? selectedGroupe.groupUsers : [];
    let accessModuleSelected = selectedGroupe ? selectedGroupe.accessModuleGrps : [];
    let accessButtonSelected = selectedGroupe ? selectedGroupe.accessButtonGrpList : [];
    let accessMenuSelected = selectedGroupe ? selectedGroupe.accessMenuGrpList : [];
    let dxForm = useRef(null);
    let formObj = useRef(_.cloneDeep(objInitialisation));

    if (selectedGroupe && (modeAside === 'CONSULT' || modeAside === 'EDIT' || modeAside === 'VALIDATE' || modeAside === 'DELETE')) {
        formObj.current = _.cloneDeep(selectedGroupe);
    } else {
        formObj.current = _.cloneDeep(objInitialisation);
    }




    useEffect(() => {

        if (allUtilisateurs && allUtilisateurs.length === 0)
            dispatch(getUtilisateur())
    })

    const validateForm = (e) => {
        let group = _.cloneDeep(formObj.current);
        let validationForm = e.validationGroup.validate().isValid;
        let data = {};
        if (modeAside === 'ADD') {
            if (validationForm) {
                data = {
                    groupe: group.groupe,
                    designation: group.designation,
                    active: group.active,
                    groupUsers: groupesSelected.map(groupes => ({
                        groupe: group.groupe,
                        username: groupes.username,

                    })),
                    accessModuleGrps: accessModuleSelected.map(AccessModuleGrp => ({
                        idGroupUser: group.groupe,
                        idModule: AccessModuleGrp.idModule
                    })),

                    accessMenuGrpList: accessMenuSelected.map(accessMenuGrp => ({
                        idGroupUser: group.groupe,
                        idMenu: accessMenuGrp.idMenu
                    })),
                    accessButtonGrpList: accessButtonSelected.map(accessButtonGrp => ({
                        idGroupUser: group.groupe,
                        idMenu: accessButtonGrp.idButton
                    }))
                };



                dxForm.current.instance.getEditor('submitAside').option("disabled", true);

                dispatch(addNewGroupe(data))
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
               let data = {
                    groupe: group.groupe,
                    designation: group.designation,
                    active: group.active,
                    groupUsers: groupesSelected.map(groupes => ({
                        groupe: group.groupe,
                        username: groupes.username,

                    })),
                    accessModuleGrps: accessModuleSelected.map(AccessModuleGrp => ({
                        idGroupUser: group.groupe,
                        idModule: AccessModuleGrp.idModule
                    })),

                    accessMenuGrpList: accessMenuSelected.map(accessMenuGrp => ({
                        idGroupUser: group.groupe,
                        idMenu: accessMenuGrp.idMenu
                    })),
                //     accessButtonGrpList: accessButtonSelected.map(accessButtonGrp => ({
                //           idGroupUser: group.groupe,
                //         idMenu: accessButtonGrp.idButton
                //     }))
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
        } else if (modeAside === 'DELETE') {

            dispatch(deleteGroupe(selectedGroupe))
                .then(() => {
                    confirmCloseAside(e);
                    notify("Success", 'success', 1000);
                }).catch(err => {
                    notify(err, 'error', 500);
                });
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

    const RenderCodeMod = () => {
        console.log("RenderCode")
        let obj = {
            title: messages.groupe,
            dataField: "groupe",
            modeAside: modeAside,
            disabled: modeAside === 'EDIT' || modeAside === 'CONSULT' || modeAside === 'DELETE'
        }
        return (
            <GroupItem >
                {Text_Template(obj)}
            </GroupItem>
        )
    }
    const Renderdesignation = () => {
        console.log("Renderdesignation")
        let obj = {
            title: "description",
            dataField: "designation",
            modeAside: modeAside,
            disabled:  modeAside === 'CONSULT' || modeAside === 'DELETE'

        }
        return (
            <GroupItem >
                {Text_Template(obj)}
            </GroupItem>
        )
    }
    const RenderActive = () => {
        console.log("RenderActive")
        let obj = {
            title: "Active",
            dataField: "active",
            modeAside: modeAside,
            disabled: modeAside === 'ADD'|| modeAside === 'CONSULT' || modeAside === 'DELETE',
            visible: modeAside !== 'ADD'
        }
        return (
            <GroupItem >
                {checkBox_Template(obj)}
            </GroupItem>
        )
    }



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
                            key={'formCreateGroupe'}
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
                                    {RenderCodeMod()}
                                    {Renderdesignation()}
                                    {RenderActive()}

                                </GroupItem>
                                <GroupItem colCount={1}>
                                    <GroupItem name="listeSousSociete" caption="les utilisateurs" >
                                        <DataGrid

                                            ref={dataGridGroupe}
                                            onSelectionChanged={onSelectionChanged}
                                            dataSource={allUtilisateurs}
                                            allowColumnReordering={true}
                                            rowAlternationEnabled={true}
                                            showBorders={true}
                                            keyExpr="username"
                                            defaultSelectedRowKeys={formObj.current.groupUsers.map(x => { return x.username })}
                                        >
                                            <Paging defaultPageSize={8} />
                                            <FilterRow visible={true} />
                                            <GroupPanel visible={true} />

                                            <SearchPanel visible={true} highlightCaseSensitive={true} />

                                            <Column
                                                dataField="username"
                                                caption="Utilisateur"
                                            />
                                            <Column
                                                dataField="designation"
                                                caption="Désignation" />

                                            <Column
                                                dataField="groupUser"
                                                caption="Appartient aussi aux groupes"
                                                cellRender={rowData => {
                                                    if (rowData && rowData.data && rowData.data.groupUsers) {
                                                        const groupes = rowData.data.groupUsers.map(group => group.groupe).join(', ');
                                                        return <span>{groupes}</span>;
                                                    } else {
                                                        return null;
                                                    }
                                                }}
                                            />




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
export default GroupeAside