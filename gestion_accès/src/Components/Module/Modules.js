import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { addTab, changeTab } from '../../Redux/Actions/MenuTabs/MenuTabs';
import './MenuAr.css';
import Header from '../Header/Header';
import { getAllModules } from '../../Redux/Actions/Module/Module';
import { useHistory } from 'react-router-dom'; // Importer useHistory

const Modules = () => {
    const dispatch = useDispatch();
    const modules = useSelector(state => state.ModuleReducer.modules);
    const module = useSelector(state => state.intl.messages.module);
    const menusTabs = useSelector(state => state.MenuTabsReducer.tabs);
    const history = useHistory(); // Initialiser useHistory

    useEffect(() => {
        dispatch(getAllModules());
    }, [dispatch]);

    const [filteredModules, setFilteredModules] = useState([]);

    useEffect(() => {
        setFilteredModules(modules);
    }, [modules]);

    const handleFilterChange = (e) => {
        const searchText = e.target.value.toLowerCase();
        const filtered = modules.filter(module => module.designation.toLowerCase().includes(searchText));
        setFilteredModules(filtered);
    };

    const moduleRedirections = {
        4: '/login', // Spécifiez les redirections pour chaque module ici
        // Ajoutez d'autres redirections au besoin
    };

    const goToPage = (e, module) => {
        e.stopPropagation();
        let title = module.desMenuP;
        if (module.descSecParent) title = `${module.descSecParent}/${module.desMenuP}`;

        // if (menusTabs && menusTabs.filter(menu => menu.key === module.codMnP).length === 0) {
        //   if (module.component) {
        //     dispatch(addTab({
        //       key: `${module.codMnP}`,
        //       title: title,
        //       icon: <i className={module.urlWeb} />,
        //       component: module.component,
        //     }));
        //   } else {
        //     dispatch(changeTab(module.codMnP));
        //   }
        // } else {
        //   dispatch(changeTab(module.codMnP));
        // }

        // Redirection dynamique en fonction de la configuration
        const redirectionUrl = moduleRedirections[module.idModule];
        if (redirectionUrl) {
            history.push(redirectionUrl);
        } else {
            history.push(`/module/${module.idModule}`);
        }
    };

    return (
        <div>
            <Header />
            <div className="dashboard">
                <div className="sidebar">
                    <input type="text" placeholder="Filter modules..." onChange={handleFilterChange} />
                    <ul>
                        {filteredModules.map(module => (
                            <li key={module.idModule} onClick={(e) => goToPage(e, module)}>
                                {module.designation}
                            </li>
                        ))}
                    </ul>
                </div>
                <div className="content">
                    {/* Content of Modules component */}
                    <section className="modulesContainer">
                        <ul id="listModules">
                            {filteredModules.map((module) => (
                                <li key={module.idModule} className="tile purple w2 h1 subMenu">
                                    <a className="link" onClick={(event) => goToPage(event, module)}>
                                <div><i className={module.urlWeb} /></div>

                                        <p className="title">{module.designation}</p>
                                    </a>
                                </li>
                            ))}
                        </ul>
                    </section>
                </div>
            </div>
        </div>
    );
};

export default Modules;
