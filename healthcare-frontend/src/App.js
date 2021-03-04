import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import HealthCareHomeComponent from './components/HomePage/HealthCareHomeComponent';
import AddUserComponent from './components/UserModule/AddUserComponent';
import UpdateUserComponent from './components/UserModule/UpdateUserComponent';
import UserListComponent from './components/UserModule/UserListComponent';
import HealthcareAudit from './components/AuditTrail/HealthcareAudit.js';
import AddPatientComponent from "./components/PatientModule/AddPatientComponent";
import PatientListComponent from "./components/PatientModule/PatientListComponent";
import UpdatePatientComponent from "./components/PatientModule/UpdatePatientComponent";

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
              <Route path = "/patients" component = {PatientListComponent}></Route>
              <Route path = "/add-patient" component = {AddPatientComponent}></Route>
              <Route path = "/update-patient/:id" component = {UpdatePatientComponent}></Route>
            </Switch>
          </div>
      </Router>
    </div>
  )
}

export default App;