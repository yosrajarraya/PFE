import React, { Component } from 'react';
import UtilisateurGrid from './UtilisateurGrid';
import UtilisateurAside from './UtilisateurAside';
import Impression from "../ComponentTable/Impression";
import ModalConfirmation from '../ComponentHelper/ModalConfirmation';
import AccessUtilisateurAside from './AccessUtilisateurAside';
/**
 * UtilisateurPage
 */
export class UtilisateurPage extends Component {
    render() {
        return (
            <div>
                <UtilisateurGrid />
                <AccessUtilisateurAside/>
                <UtilisateurAside />
                <ModalConfirmation reducer="UtilisateurAsideReducer" />
                <ModalConfirmation reducer="UtilisateurAccessAsideReducer" />
                <Impression />

            </div>
        );
    }
}

export default UtilisateurPage;