import React, { useRef } from 'react';
import { useDispatch, useSelector } from "react-redux";
import store from '../../Redux/Store/Store';
import RessourcesGrp from "../../Helper/RessourcesGrp";
import Ressources from "../../Helper/Ressources";

import {
    handleOpenAddMode,
    handleOpenEditMode,
    handleOpenDeleteMode,
    handleOpenConsultMode,
    handleOpenimprimMode
} from "../../Redux/Actions/Groupe/GroupeAside";
import {
    handleOpenAccessMode
} from "../../Redux/Actions/Groupe/GroupeAccessAside";
import {
    handleOpenModal
} from "../../Redux/Actions/ComponentTable/ModalImpression";
import notify from "devextreme/ui/notify";
import { notifyOptions } from '../../Helper/Config';
import { loadMessages } from "devextreme/localization";
import arMessages from "../../i18n/datagrid_ar.json";
import enMessages from "devextreme/localization/messages/en";
import frMessages from "devextreme/localization/messages/fr";
import Helper from '../../Helper/Helper';
import HelperGrid from '../../Helper/HelperGrid';
import TableGrid from '../ComponentHelper/TableGrid';
import { getGroupeByCode,imprime } from "../../Redux/Actions/Groupe/Groupe";

loadMessages(arMessages);
loadMessages(enMessages);
loadMessages(frMessages);

let selectionChangedRaised;
const GroupeGrid = () => {

    const dispatch = useDispatch();
    const GroupesReducer = useSelector(state => state.GroupesReducer);
    const messages = useSelector(state => state.intl.messages);
    const dataGrid = useRef(null);


    const onSelectionChanged = ({ selectedRowsData }) => {
        selectionChangedRaised = true;
        HelperGrid.handleSelectionChanged(selectedRowsData, GroupesReducer);
    };
    const onRowClick = e => {
        if (!selectionChangedRaised) {
            let dataGrid = e.component;
            let keys = dataGrid.getSelectedRowKeys();
            if (dataGrid.getSelectedRowKeys().length > 0)
                dataGrid.deselectRows(keys);
        }
        selectionChangedRaised = false;
    };

    const onToolbarPreparing = (e) => {
        let filtres = {
            filterRemove: {
                visible: true
            }
        }
        let buttons = {
            columnChooserButton: {
                visible: true,
            },
            refresh: {
                visible: true,
            },
            add: {
                visible: true,
                action: onClickBtnAdd
            },
            edit: {
                visible: true,
                action: onClickBtnEdit
            },
            consult: {
                visible: true,
                action: onClickBtnConsult
            },
            delete: {
                visible: true,
                action: onClickBtnDelete
            },
            access: {
                visible: true,
                action: onClickBtnAccess
            },
            editionList: {
                visible: true,
                action: onClickBtnEditionList
            },
            edition: {
                visible: true,
                action: onClickBtnEdition
            },
            imprime: {
                visible: true,
                action: onClickBtnimprime
            },
            export_excel: {
                visible: true
            }
        }
        HelperGrid.handleToolbarPreparing(e, dataGrid, buttons, filtres, GroupesReducer)
    }
    const onClickBtnAdd = () => {
        dispatch(handleOpenAddMode(refreshDataGrid))
    }
    const onClickBtnEdit = () => {
        if (dataGrid.current !== null) {
            let dataGridInstance = dataGrid.current.instance;
            let selectedRowKeys = dataGridInstance.getSelectedRowKeys()[0];
            dispatch(getGroupeByCode(selectedRowKeys))
                .then((data) => {
                    dispatch(handleOpenEditMode(data, refreshDataGrid))
                })
        }
    }
    const onClickBtnConsult = () => {
        if (dataGrid.current !== null) {
            let dataGridInstance = dataGrid.current.instance;
            let selectedRowKeys = dataGridInstance.getSelectedRowKeys()[0];
            dispatch(getGroupeByCode(selectedRowKeys))
                .then((data) =>
                    dispatch(handleOpenConsultMode(data, refreshDataGrid))
                )
        }
    }
    const onClickBtnDelete = () => {
        if (dataGrid.current !== null) {
            let dataGridInstance = dataGrid.current.instance;
            let selectedRowKeys = dataGridInstance.getSelectedRowKeys()[0];
            dispatch(getGroupeByCode(selectedRowKeys))
                .then((data) => {
                    dispatch(handleOpenDeleteMode(data, refreshDataGrid))
                }
                )
        }
    }
    const onClickBtnAccess = () => {
        if (dataGrid.current !== null) {
            let dataGridInstance = dataGrid.current.instance;
            let selectedRowKeys = dataGridInstance.getSelectedRowKeys()[0];
            dispatch(getGroupeByCode(selectedRowKeys))
                .then((data) => {
                    dispatch(handleOpenAccessMode(data, refreshDataGrid))
                }
                )
        }
    }
    const onClickBtnimprime = () => {
        dispatch(imprime())
            .then((blob) => {
                const url = window.URL.createObjectURL(blob);
                const a = document.createElement('a');
                a.style.display = 'none';
                a.href = url;
                a.download = 'group_report.pdf'; // Adjust file name and extension as needed
                document.body.appendChild(a);
                a.click();
                window.URL.revokeObjectURL(url);
                document.body.removeChild(a);
                dispatch(handleOpenimprimMode(blob, refreshDataGrid));
            })
            .catch((error) => {
                console.error('File download failed:', error);
            });
    };
    const onClickBtnEditionList = () => {
        /*  dispatch(handleOpenModal())
         let du = GroupesReducer.dateDebut;
         let au = GroupesReducer.dateFin;
         let url = `${Ressources.CoreUrlB}/${Ressources.Securite.api}/${Ressources.Securite.Demande}/edition/listeGroupes?du=${du}&au=${au}`;
         impression(url); */
    }
    const onClickBtnEdition = () => {
        /*    if (dataGrid.current !== null) {
               let dataGridInstance = dataGrid.current.instance;
               let selectedRowKeys = dataGridInstance.getSelectedRowKeys()[0];
               dispatch(handleOpenModal())
               let url = `${Ressources.CoreUrlB}/${Ressources.Securite.api}/${Ressources.Securite.Demande}/edition/${selectedRowKeys}`;
               impression(url);
           } */
    }
    const refreshDataGrid = () => {
        if (dataGrid.current !== null)
            dataGrid.current.instance.refresh();
    }


    return (
        <TableGrid
            dataGrid={dataGrid}
            keyExpr='groupe'
            customStore={HelperGrid.constructCustomStore(`${`${Ressources.CoreUrlB}/${Ressources.Securite.api}/${Ressources.Securite.Groupes}/filtre`}`,
                'groupe')}
            onToolbarPreparing={onToolbarPreparing}
            onSelectionChanged={onSelectionChanged}
            onRowClick={onRowClick}
            fileName={messages.budgets}
            columns={[
                {
                    dataField: 'groupe',
                    caption: "Groupe"

                },
                {
                    dataField: 'designation',
                    caption: "Désignation"
                },
                
                {
                    dataField: 'userCreation',
                    caption: "Utilisateur de Création"
                },
                {
                    dataField: 'dateCreation',
                    caption: "Date de creation"
                },
                {
                    dataField: 'active',
                    caption: "Active"
                },

            ]
            }
            templates={
                []
            }
        />
    )
}

export default GroupeGrid

