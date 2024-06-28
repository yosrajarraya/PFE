import React, { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';
import { BrowserRouter, Route, Switch, Redirect } from 'react-router-dom';
import MenuTabs from './Components/MenuTabs/MenuTabs';
import LoginForm from './Components/LoginForm/LoginForm';
import RouteGuard from './Components/Route/RouteGuard';
import UtilisateurAside from './Components/Utilisateur/UtilisateurAside';
export const Router = () => {
  const userAuthentification = useSelector(state => state.LoginReducer.userAuthentification);

  // État local pour l'authentification
  const [authenticated, setAuthenticated] = useState(false);

  useEffect(() => {
    // Vérifier si l'utilisateur est authentifié dans le stockage local
    const userAuthenticated = localStorage.getItem('userAuthenticated');
    if (userAuthenticated === 'true') {
      setAuthenticated(true);
    }
  }, []);

  const authenticatedUser = useSelector(state => state.LoginReducer.authenticatedUser) || localStorage.getItem('authenticatedUser');

  return (
    <BrowserRouter>
      <Switch>
        <RouteGuard exact path="/login">
          {userAuthentification || authenticated ? <Redirect to="/" /> : <LoginForm />}
        </RouteGuard>
        <Route exact path="/">
          {userAuthentification || authenticated ? <MenuTabs /> : <Redirect to="/login" />}
          <UtilisateurAside authenticatedUser={authenticatedUser} />
        </Route>
      </Switch>
    </BrowserRouter>
  );
};

export default Router;
