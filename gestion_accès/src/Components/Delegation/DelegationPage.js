import React, {Component} from 'react';
import DelegationGrid from './DelegationGrid';
import DelegationAside from './DelegationAside';
import Impression from "../ComponentTable/Impression";
import ModalConfirmation from '../ComponentHelper/ModalConfirmation';

/**
 * DemandePage
 */
export class DemandePage extends Component {
    render() {
        return (
            <div>
                <DelegationGrid/>
                <DelegationAside/> 
                <ModalConfirmation reducer = "DelegationAsideReducer"/>
                <Impression/>
                
            </div>
        );
    }
}

export default DemandePage;