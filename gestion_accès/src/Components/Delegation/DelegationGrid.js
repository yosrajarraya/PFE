import React, { useRef } from 'react';
import { useDispatch, useSelector } from "react-redux";
import Ressources from "../../Helper/Ressources";
import {
    handleOpenAddMode,
    handleOpenEditMode,
    handleOpenDeleteMode,
    handleOpenConsultMode
} from "../../Redux/Actions/Delegation/DelegationAside";
import notify from "devextreme/ui/notify";
import { notifyOptions } from '../../Helper/Config';
import { loadMessages } from "devextreme/localization";
import arMessages from "../../i18n/datagrid_ar.json";
import enMessages from "devextreme/localization/messages/en";
import frMessages from "devextreme/localization/messages/fr";
import Helper from '../../Helper/Helper';
import HelperGrid from '../../Helper/HelperGrid';
import TableGrid from '../ComponentHelper/TableGrid';
import { getDelegationbyCode, deleteDelegation } from "../../Redux/Actions/Delegation/Delegation";

loadMessages(arMessages);
loadMessages(enMessages);
loadMessages(frMessages);

let selectionChangedRaised;
const DelegationGrid = () => {

    const dispatch = useDispatch();
    const DelegationsReducer = useSelector(state => state.DelegationsReducer);
    const messages = useSelector(state => state.intl.messages);
    const dataGrid = useRef(null);

    const onSelectionChanged = ({ selectedRowsData }) => {
        selectionChangedRaised = true;
        HelperGrid.handleSelectionChanged(selectedRowsData, DelegationsReducer);
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
    /**
     * 
     * @param {*} e 
     * obj filtres.select : select demande
     */
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
            export_excel: {
                visible: true
            }
        }
        HelperGrid.handleToolbarPreparing(e, dataGrid, buttons, filtres, DelegationsReducer)
    }
    const onClickBtnAdd = () => {
        dispatch(handleOpenAddMode(refreshDataGrid))
    }

    const onClickBtnEdit = () => {
        let dataGridInstance = dataGrid.current.instance;
        HelperGrid.disableEnableAllButtons(DelegationsReducer, true);
        let selectedRowKeys = dataGridInstance.getSelectedRowKeys()[0];
        let t = dispatch(getDelegationbyCode(selectedRowKeys))
        t.then((data) => {
            dispatch(handleOpenEditMode(data));
            dataGridInstance.clearSelection();
        });
    }
    const onClickBtnDelete = () => {
        if (dataGrid.current !== null) {
            let dataGridInstance = dataGrid.current.instance;
            let selectedRowKeys = dataGridInstance.getSelectedRowKeys()[0];
            dispatch(getDelegationbyCode(selectedRowKeys))
                .then((data) => {
                    if (data.userValidate === null) {
                        notifyOptions.message = messages.DelegationValidee
                        notify(notifyOptions, 'error', notifyOptions.displayTime);
                    }
                    else {
                        dispatch(handleOpenDeleteMode(data, refreshDataGrid))
                    }
                }
                )
        }
    }
    const onClickBtnConsult = () => {
        if (dataGrid.current !== null) {
            let dataGridInstance = dataGrid.current.instance;
            let selectedRowKeys = dataGridInstance.getSelectedRowKeys()[0];
            dispatch(getDelegationbyCode(selectedRowKeys))
                .then((data) =>
                    dispatch(handleOpenConsultMode(data, refreshDataGrid))
                )
        }
    }
    const refreshDataGrid = () => {
        if (dataGrid.current !== null)
            dataGrid.current.instance.refresh();
    }
    const renderDateFormat = (data) => {
        return Helper.formatDate(data.value);
    };
    async function impression(url) {
        const response = await fetch(url);
        const blob = await response.blob();
        componentImpression(blob);

    }
    const componentImpression = (blob) => {
        let url = URL.createObjectURL(blob);
        document.getElementById('iframe_content').src = url;
    }

    return (
        <TableGrid
            dataGrid={dataGrid}
            keyExpr='numDelegation'
            customStore={HelperGrid.constructCustomStore(`${`${Ressources.CoreUrlB}/${Ressources.Securite.api}/${Ressources.Securite.Delegation}/filter`}`,
                'numDelegation')}
            onToolbarPreparing={onToolbarPreparing}
            onSelectionChanged={onSelectionChanged}
            onRowClick={onRowClick}
            fileName={messages.demandes}
            columns={[

                {
                    dataField: 'userDelegant',
                    caption: "Utilisateur"
                },
                {
                    dataField: 'motif',
                    caption: "Motif",
                },
                {
                    dataField: 'userCreation',
                    caption: messages.userCreate
                },

                {
                    dataField: 'dateDebut',
                    caption: "dateDebut",
                    dataType: 'date',
                    format: 'dd/MM/yyyy'

                },
                {
                    dataField: 'dateFin',
                    caption: "dateFin",
                    dataType: 'date',
                    format: 'dd/MM/yyyy'
                },
                {
                    dataField: 'dateCreation',
                    caption: "dateCreation",
                    dataType: 'datetime',
                    format: 'dd/MM/yyyy HH:mm'
                },
                {
                    dataField: 'declenche',
                    caption: "declenche"

                },
                {
                    dataField: 'termine',
                    caption: "termine"

                },
            ]
            }
            templates={
                []
            }
        />
    )
}

export default DelegationGrid
