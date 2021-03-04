import React, { Component } from 'react';
import TextField from '@material-ui/core/TextField';
import { Container, Box } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import SaveIcon from '@material-ui/icons/Save';
import Button from '@material-ui/core/Button';
import PatientService from '../../services/PatientService';

class AddPatientComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            patientId: '',
            patientFirstName: '',
            patientLastName: '',
            dob: '',
            age: '',
            gender: '',
            maritalStatus: '',
            contactNo: '',
            emailId: '',
            address: '',
            postalCode: '',
            city: '',
            country: '',
            regDate: ''
        }

        this.updatePatientId = this.updatePatientId.bind(this);
        this.updateFirstName = this.updateFirstName.bind(this);
        this.updateLastName = this.updateLastName.bind(this);
        this.updateDOB = this.updateDOB.bind(this);
        this.updateAge = this.updateAge.bind(this);
        this.updateGender = this.updateGender.bind(this);
        this.updateMaritalStatus = this.updateMaritalStatus.bind(this);
        this.updateContactNo = this.updateContactNo.bind(this);
        this.updateEmailId = this.updateEmailId.bind(this);
        this.updateAddress = this.updateAddress.bind(this);
        this.updatePostalCode = this.updatePostalCode.bind(this);
        this.updateCity = this.updateCity.bind(this);
        this.updateCountry = this.updateCountry.bind(this);
        this.updateRegDate = this.updateRegDate.bind(this);
        this.onSave = this.onSave.bind(this);
    }

    classes = makeStyles((theme) => ({
        root: {
            '& .MuiTextField-root': {
                margin: theme.spacing(2),
                width: '25ch',
            },
        },
    }));

    updatePatientId = (event) => {
        this.setState({ patientId: event.target.value });
    }

    updateFirstName = (event) => {
        this.setState({ patientFirstName: event.target.value });
    }

    updateLastName = (event) => {
        this.setState({ patientLastName: event.target.value });
    }

    updateDOB = (event) => {
        this.setState({ dob: event.target.value });
    }

    updateAge = (event) => {
        this.setState({ age: event.target.value });
    }

    updateMaritalStatus = (event) => {
        this.setState({ maritalStatus: event.target.value });
    }

    updateContactNo = (event) => {
        this.setState({ contactNo: event.target.value });
    }

    updateGender = (event) => {
        this.setState({ gender: event.target.value });
    }

    updateEmailId = (event) => {
        this.setState({ emailId: event.target.value });
    }

    updateAddress = (event) => {
        this.setState({ address: event.target.value });
    }

    updatePostalCode = (event) => {
        this.setState({ postalCode: event.target.value });
    }

    updateCity = (event) => {
        this.setState({ city: event.target.value });
    }

    updateCountry = (event) => {
        this.setState({ country: event.target.value });
    }
    
    updateRegDate = (event) => {
        this.setState({ regDate: event.target.value });
    }

    onSave = (event) => {
        let patient = {
            patientId: this.state.patientId,
            patientFirstName: this.state.patientFirstName,
            patientLastName: this.state.patientLastName,
            dob: this.state.dob,
            age: this.state.age,
            gender: this.state.gender,
            contactNo: this.state.contactNo,
            emailId: this.state.emailId,
            maritalStatus: this.state.maritalStatus,
            address: this.state.address,
            city: this.state.city,
            country: this.state.country,
            postalCode: this.state.postalCode,
            regDate: this.state.regDate
        }
        if((patient.patientId !== undefined && patient.patientId !== null && patient.patientId !== "") &&
            (patient.patientFirstName !== undefined && patient.patientFirstName !== null && patient.patientFirstName !== "")){
            PatientService.addPatient(patient).then(res => {
                this.props.history.push("/patients");
            });
        }
    
    }

    render() {
        return (
            <Container maxWidth="sm">
                <div>
                    <Box mt={3}></Box>
                    <form className={this.classes.root} noValidate autoComplete="off">
                        <div>
                        <Box mt={3}></Box>
                            <TextField
                                required
                                id="patientId"
                                label="Patient Id"
                                variant="outlined"
                                onChange={this.updatePatientId}
                            />
                            <Box mt={3}></Box>
                            <TextField
                                id="patientFirstName"
                                label="First Name"
                                variant="outlined"
                                onChange={this.updateFirstName}
                            />
                           <Box mt={3}></Box>
                            <TextField
                                id="patientLastName"
                                label="Last Name"
                                variant="outlined"
                                onChange={this.updateLastName}
                            />
                            <Box mt={3}></Box>
                            <TextField
                                id="dob"
                                label="DOB"
                                type="date"
                                onChange={this.updateDOB}
                                InputLabelProps={{
                                shrink: true,
                                }}
                            />
                            <Box mt={3}></Box>
                            <TextField
                                id="age"
                                label="Age"
                                variant="outlined"
                                onChange={this.updateAge}
                            />
                            <Box mt={3}></Box>
                            <TextField
                                id="gender"
                                label="Gender"
                                variant="outlined"
                                onChange={this.updateGender}
                            />
                            <Box mt={3}></Box>
                            <TextField
                                id="maritalStatus"
                                label="Marital Status"
                                variant="outlined"
                                onChange={this.updateMaritalStatus}
                            />
                            <Box mt={3}></Box>
                            <TextField
                                id="contactNo"
                                label="Mobile Number"
                                variant="outlined"
                                onChange={this.updateContactNo}
                            />
                            <Box mt={3}></Box>
                            <TextField
                                id="emailId"
                                label="Email Id"
                                variant="outlined"
                                onChange={this.updateEmailId}
                            />
                            <Box mt={3}></Box>
                            <TextField
                                id="address"
                                label="Address"
                                variant="outlined"
                                onChange={this.updateAddress}
                            />
                            <Box mt={3}></Box>
                            <TextField
                                id="city"
                                label="City"
                                variant="outlined"
                                onChange={this.updateCity}
                            />
                            <Box mt={3}></Box>
                            <TextField
                                id="country"
                                label="Country"
                                variant="outlined"
                                onChange={this.updateCountry}
                            />
                            <Box mt={3}></Box>
                            <TextField
                                id="postalCode"
                                label="Postal Code"
                                variant="outlined"
                                onChange={this.updatePostalCode}
                            />
                            <Box mt={3}></Box>
                            <TextField
                                id="regDate"
                                label="Appointment Date"
                                type="date"
                                onChange={this.updateRegDate}
                                InputLabelProps={{
                                shrink: true,
                                }}
                            />
                        </div>
                        <Box mt={3}>
                            <Button
                                variant="contained"
                                color="primary"
                                onClick={this.onSave}
                                size="small"
                                startIcon={<SaveIcon />}>
                                Save Record
                            </Button>
                            <Button
                                variant="contained"
                                color="secondary"
                                size="small"
                                href="http://localhost:3000/patients">
                                Cancel Record
                        </Button>
                        </Box>
                    </form>
                </div>
            </Container>
        );
    }
}



export default AddPatientComponent;