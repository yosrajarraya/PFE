import React, { Component } from 'react';
import GroupeGrid from './GroupeGrid';
import GroupeAside from './GroupeAside';
import Impression from "../ComponentTable/Impression";
import ModalConfirmation from '../ComponentHelper/ModalConfirmation';
import AccessGroupeAside from './AccessGroupeAside';

/**
 * GroupePage
 */
export class GroupePage extends Component {
    render() {
        return (
            <div>
                <GroupeGrid />
                <AccessGroupeAside />
                <GroupeAside />
                <ModalConfirmation reducer="GroupeAsideReducer" />
                <Impression />

            </div>
        );
    }
}

export default GroupePage;
