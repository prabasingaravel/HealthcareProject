import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import HealthCareHomeComponent from './components/HomePage/HealthCareHomeComponent';
import AddUserComponent from './components/UserModule/AddUserComponent';
import UpdateUserComponent from './components/UserModule/UpdateUserComponent';
import UserListComponent from './components/UserModule/UserListComponent';
import HealthcareAudit from './components/AuditTrail/HealthcareAudit.js';

function App() {
  return (
    <div>
      <Router>
          <div>
            <Switch>
              <Route path = "/" exact component = {HealthCareHomeComponent}></Route>
              <Route path = "/users" component = {UserListComponent}></Route>
              <Route path = "/add-user" component = {AddUserComponent}></Route>
              <Route path = "/update-user/:id" component = {UpdateUserComponent}></Route>
              <Route path = "/audit" component = {HealthcareAudit}></Route>
            </Switch>
          </div>
      </Router>
    </div>
  )
}

export default App;