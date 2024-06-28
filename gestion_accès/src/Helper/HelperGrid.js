import store from '../Redux/Store/Store';
import filterRemove from '../assests/css/images/filter-remove.png';
import CustomStore from 'devextreme/data/custom_store';
import Helper from './Helper';
import notify from "devextreme/ui/notify";
import { notifyOptions } from './Config';
import DropDownButton from 'devextreme-react/drop-down-button';

const HelperGrid = {
    handleToolbarPreparing: function (e, dataGrid, buttons, filtres, Reducer) {
        const messages = store.getState().intl.messages;
        let disableButtons = true;
        let toolbarItems = e.toolbarOptions.items;
        let listSearchPanel = toolbarItems.filter(item => item.name === "searchPanel");
        let searchPanel;
        if (listSearchPanel.length > 0) {
            searchPanel = listSearchPanel[0];
            searchPanel.location = "before";
        }

        let listColumnChooserButton = toolbarItems.filter(item => item.name === "columnChooserButton");
        let columnChooserButton;
        if (listColumnChooserButton.length > 0) {
            columnChooserButton = listColumnChooserButton[0];
            columnChooserButton.location = "before";
            columnChooserButton.options.elementAttr = { "class": "toolbar-button" };
            columnChooserButton.visible = buttons !== undefined && buttons.columnChooserButton !== undefined;
        }
        e.toolbarOptions.items = [];
        e.toolbarOptions.items.push(
            searchPanel !== undefined ? searchPanel : '',
            {
                location: 'center',
                template: 'filtreSelect',
                widget: 'dxSelectBox',
                visible: filtres !== undefined && filtres.select !== undefined
            },
            {
                location: 'center',
                template: 'filtreDate',
                visible: filtres !== undefined && filtres.dates !== undefined
            },
            {
                widget: 'dxButton',
                location: "center",
                visible: buttons !== undefined && buttons.refresh !== undefined,
                options: {
                    icon: 'refresh',
                    elementAttr: {
                        "class": "toolbar-button"
                    },
                    onClick: () => {
                        if (dataGrid.current !== null) dataGrid.current.instance.refresh()
                    }
                }
            },
            {
                widget: 'dxButton',
                location: "before",
                visible: filtres !== undefined && filtres.filterRemove !== undefined,
                options: {
                    icon: filterRemove,
                    elementAttr: {
                        "class": "toolbar-button"
                    },
                    onClick: () => {
                        if (dataGrid.current !== null) dataGrid.current.instance.clearFilter()
                    }
                }
            },
            columnChooserButton,
            {
                widget: 'dxButton',
                location: "after",
                visible: buttons.add !== undefined && buttons.add.visible,
                options: {
                    icon: 'fas fa-plus fa-3x green',
                    text: messages.add,
                    onClick: () => {
                        buttons.add.action()
                    }
                }
            },
            {
                widget: 'dxButton',
                location: "after",
                visible: buttons.edit !== undefined && buttons.edit.visible,
                options: {
                    icon: 'edit',
                    text: messages.edit,
                    disabled: disableButtons,
                    onInitialized: (args) => {
                        if (Reducer !== undefined) Reducer.btnEditInstance = args.component;
                    },
                    onClick: () => {
                        buttons.edit.action()
                    }
                }
            },
            {
                widget: 'dxButton',
                location: "after",
                visible: buttons.access !== undefined && buttons.access.visible,
                options: {
                    icon: 'fas fa-key ',
                    text: messages.access,
                    disabled: disableButtons,
                    onInitialized: (args) => {
                        if (Reducer !== undefined) Reducer.btnAccessInstance = args.component;
                    },
                    onClick: () => {
                        buttons.access.action()
                    }
                }
            },
            {
                widget: 'dxButton',
                location: "after",
                visible: buttons.validate !== undefined && buttons.validate.visible,
                options: {
                    icon: 'save',
                    text: messages.validate,
                    disabled: disableButtons,
                    onInitialized: (args) => {
                        if (Reducer !== undefined) Reducer.btnValidateInstance = args.component;
                    },
                    onClick: () => {
                        buttons.validate.action()
                    }
                }
            },
            {
                widget: 'dxButton',
                location: "after",
                visible: buttons.consult !== undefined && buttons.consult.visible,
                options: {
                    icon: 'fas fa-eye greenLight',
                    text: messages.consult,
                    disabled: disableButtons,
                    onInitialized: (args) => {
                        if (Reducer !== undefined) Reducer.btnConsultInstance = args.component;
                    },
                    onClick: () => {
                        buttons.consult.action()
                    }
                }
            },
            {
                widget: 'dxButton',
                location: "after",
                visible: buttons.delete !== undefined && buttons.delete.visible,
                options: {
                    icon: 'trash',
                    text: messages.delete,
                    disabled: disableButtons,
                    onInitialized: (args) => {
                        if (Reducer !== undefined) Reducer.btnDeleteInstance = args.component;
                    },
                    onClick: () => {
                        buttons.delete.action()
                    }
                }
            },
            {
                widget: 'dxButton',
                location: "after",
                visible: buttons.imprime !== undefined && buttons.imprime.visible,
                options: {
                    icon: 'print',
                    text: messages.imprime,
                    onClick: () => {
                        buttons.imprime.action()
                    }
                }
            },
            {
                location: "after",
                widget: 'dxButton',
                visible: buttons.export_excel !== undefined && buttons.export_excel.visible,
                options: {
                    name: messages.Excel,
                    icon: 'exportxlsx',
                    onClick: function () {
                        buttons.export_excel.action ?
                            buttons.export_excel.action() :
                            e.component.exportToExcel(false);
                    },
                }
            }
        )
    },

    showPrintInterface: function () {
        window.print();
    },
    onContentReady: () => {
        let listNodata = document.getElementsByClassName('dx-datagrid-nodata');
        for (const element of listNodata) {
            element.innerText = 'Aucun donnée disponible';
        }
    },
    refreshDataGrid: (dataGrid) => {
        if (dataGrid.current !== null)
            dataGrid.current.instance.refresh();
    },
    clearDataGrid: function (dataGrid) {
        if (dataGrid.current !== null)
            dataGrid.current.instance.clearFilter()
    },
    disableEnableAllButtons: function (Reducer, value) {
        // if (Reducer.btnAddInstance !== undefined) Reducer.btnAddInstance.option('disabled', value);
        if (Reducer.btnConsultInstance !== undefined) Reducer.btnConsultInstance.option('disabled', value);
        // if (Reducer.btnEditionInstance !== undefined) Reducer.btnEditionInstance.option('disabled', value);
        if (Reducer.btnEditInstance !== undefined) Reducer.btnEditInstance.option('disabled', value);
        if (Reducer.btnValidateInstance !== undefined) Reducer.btnValidateInstance.option('disabled', value);
        if (Reducer.btnDeleteInstance !== undefined) Reducer.btnDeleteInstance.option('disabled', value);
    },
    handleSelectionChanged: function (selectedRowsData, Reducer) {
        const disableButtons = true;

        const updateButtonState = (buttonInstance, state) => {
            if (buttonInstance !== undefined && buttonInstance !== null) {
                buttonInstance.option('disabled', state);
            }
        }

        if (selectedRowsData.length === 0) {
            updateButtonState(Reducer.btnConsultInstance, disableButtons);
            updateButtonState(Reducer.btnEditInstance, disableButtons);
            updateButtonState(Reducer.btnValidateInstance, disableButtons);
            updateButtonState(Reducer.btnAccessInstance, disableButtons);
            updateButtonState(Reducer.btnDeleteInstance, disableButtons);
            updateButtonState(Reducer.btnEditionInstance, disableButtons);
        } else {
            updateButtonState(Reducer.btnConsultInstance, !disableButtons);
            updateButtonState(Reducer.btnEditInstance, !disableButtons);
            updateButtonState(Reducer.btnValidateInstance, !disableButtons);
            updateButtonState(Reducer.btnAccessInstance, !disableButtons);
            updateButtonState(Reducer.btnDeleteInstance, !disableButtons);
            updateButtonState(Reducer.btnEditionInstance, !disableButtons);
        }
    },

    constructCustomStore: function (url, key) {
        return new CustomStore({
            key: key,
            load: async (loadOptions) => {
                try {
                    const response = await fetch(eval('`' + `${url}` + '`'));
                    const data = await response.json();
                    return {
                        data: data
                    };
                } catch (e) {
                    throw 'Data Loading Error';
                }
            }
        })
    }
};

export default HelperGrid;
