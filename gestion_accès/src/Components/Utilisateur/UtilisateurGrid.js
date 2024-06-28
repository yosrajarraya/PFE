import React, { useRef } from 'react';
import { useDispatch, useSelector } from "react-redux";
import Ressources from "../../Helper/Ressources";

import {
    handleOpenAddMode,
    handleOpenEditMode,
    handleOpenDeleteMode,
    handleOpenConsultMode,
    handleOpenimprimMode
} from "../../Redux/Actions/Utilisateur/UtilisateurAside";
import { loadMessages } from "devextreme/localization";
import arMessages from "../../i18n/datagrid_ar.json";
import enMessages from "devextreme/localization/messages/en";
import frMessages from "devextreme/localization/messages/fr";
import Helper from '../../Helper/Helper';
import HelperGrid from '../../Helper/HelperGrid';
import TableGrid from '../ComponentHelper/TableGrid';
import { getUtilisateurByCode ,imprime} from "../../Redux/Actions/Utilisateur/Utilisateur";
import {
    handleOpenAccessMode
} from "../../Redux/Actions/Utilisateur/UtilisateurAccessAside";
loadMessages(arMessages);
loadMessages(enMessages);
loadMessages(frMessages);

let selectionChangedRaised;
const UtilisateurGrid = () => {

    const dispatch = useDispatch();
    const UtilisateursReducer = useSelector(state => state.UtilisateursReducer);
    const messages = useSelector(state => state.intl.messages);
    const dataGrid = useRef(null);


    const onSelectionChanged = ({ selectedRowsData }) => {
        selectionChangedRaised = true;
        HelperGrid.handleSelectionChanged(selectedRowsData, UtilisateursReducer);
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
            imprime: {
                visible: true,
                action: onClickBtnimprime
            },
            export_excel: {
                visible: true
            }
        }
        HelperGrid.handleToolbarPreparing(e, dataGrid, buttons, filtres, UtilisateursReducer)
    }
    const onClickBtnAdd = () => {
        dispatch(handleOpenAddMode(refreshDataGrid))
    }
    const onClickBtnEdit = () => {
        if (dataGrid.current !== null) {
            let dataGridInstance = dataGrid.current.instance;
            let selectedRowKeys = dataGridInstance.getSelectedRowKeys()[0];
            dispatch(getUtilisateurByCode(selectedRowKeys))
                .then((data) => {
                    dispatch(handleOpenEditMode(data, refreshDataGrid))
                })
        }
    }
    const onClickBtnConsult = () => {
        if (dataGrid.current !== null) {
            let dataGridInstance = dataGrid.current.instance;
            let selectedRowKeys = dataGridInstance.getSelectedRowKeys()[0];
            dispatch(getUtilisateurByCode(selectedRowKeys))
                .then((data) =>
                    dispatch(handleOpenConsultMode(data, refreshDataGrid))
                )
        }
    }
    const onClickBtnDelete = () => {
        if (dataGrid.current !== null) {
            let dataGridInstance = dataGrid.current.instance;
            let selectedRowKeys = dataGridInstance.getSelectedRowKeys()[0];
            dispatch(getUtilisateurByCode(selectedRowKeys))
                .then((data) => {
                    dispatch(handleOpenDeleteMode(data, refreshDataGrid))
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
                a.download = 'utilisateurs_report.pdf'; // Adjust file name and extension as needed
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
    const onClickBtnAccess = () => {
        if (dataGrid.current !== null) {
            let dataGridInstance = dataGrid.current.instance;
            let selectedRowKeys = dataGridInstance.getSelectedRowKeys()[0];
            dispatch(getUtilisateurByCode(selectedRowKeys))
                .then((data) => {
                    dispatch(handleOpenAccessMode(data, refreshDataGrid))
                }
                )
        }
    }
    
    const refreshDataGrid = () => {
        if (dataGrid.current !== null)
            dataGrid.current.instance.refresh();
    }
  

    return (
        <TableGrid
        dataGrid={dataGrid}
        keyExpr='username'
        customStore={HelperGrid.constructCustomStore(`${Ressources.CoreUrlB}/${Ressources.Securite.api}/${Ressources.Securite.Utilisateur}/filtre`, 'username')}
        onToolbarPreparing={onToolbarPreparing}
        onSelectionChanged={onSelectionChanged}
        onRowClick={onRowClick}
        fileName={messages.budgets}
        columns={[
            {
                dataField: 'username',
                caption: "Utilisateur"
            },
            {
                dataField: 'designation',
                caption: "Désignation"
            },
            {
                dataField: 'groups',
                caption: 'Appartient aux groupes',
                cellRender: rowData => {
                    if (rowData && rowData.data && rowData.data.groupUsers) {
                        const groupes = rowData.data.groupUsers.map(group => group.groupe).join(', ');
                        return <span>{groupes}</span>;
                    } else {
                        return null;
                    }
                }
            },

            {
                dataField: 'userCreation',
                caption: "Utilisateur de création"
            },
            {
                dataField: 'dateCreation',
                caption: "Date de creation"
            },
            ,
            {
                dataField: 'active',
                caption: "Active"
            },
    
        ]}
        templates={[]}
    />
    )
}

export default UtilisateurGrid