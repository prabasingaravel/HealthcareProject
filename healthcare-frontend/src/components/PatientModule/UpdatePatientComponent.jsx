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
            patientId: this.props.match.params.id,
            patientFirstName: '',
            patinetLastName: '',
            dob: '',
            age: '',
            gender: '',
            maritalStatus: '',
            contactNo: '',
            emailId: '',
            address: '',
            city: '',
            country: '',
            postalCode: '',
            regDate: ''
        }

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
        this.onUpdate = this.onUpdate.bind(this);
    }

    classes = makeStyles((theme) => ({
        root: {
            '& .MuiTextField-root': {
                margin: theme.spacing(2),
                width: '25ch',
            },
        },
    }));

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

    onUpdate = (event) => {
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
        if ((patient.patientId !== undefined && patient.patientId !== null && patient.patientId !== "") &&
            (patient.patientFirstName !== undefined && patient.patientFirstName !== null && patient.patientFirstName !== "")) {
            PatientService.updatePatient(patient).then(res => {
                this.props.history.push("/patients");
            });
        }

    }

    componentDidMount() {
        PatientService.getPatientById(this.state.patientId).then((res) => {
            let patient = res.data;
            this.setState({ patientFirstName: patient.patientFirstName });
            this.setState({ patientLastName: patient.patientLastName});
            this.setState({ dob: patient.dob.slice(0, 10) });
            this.setState({ age: patient.age });
            this.setState({ gender: patient.gender });
            this.setState({ maritalStatus: patient.maritalStatus });
            this.setState({ contactNo: patient.contactNo });
            this.setState({ emailId: patient.emailId });
            this.setState({ address: patient.address });
            this.setState({ city: patient.city });
            this.setState({ country: patient.country });
            this.setState({ postalCode: patient.postalCode });
            this.setState({ regDate: patient.regDate.slice(0, 10) });
        });
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
                                id="patientFirstName"
                                label="First Name"
                                variant="outlined"
                                onChange={this.updateFirstName}
                                value={this.state.patientFirstName}
                            />
                            <Box mt={3}></Box>
                            <TextField
                                id="patientLastName"
                                label="Last Name"
                                variant="outlined"
                                onChange={this.updateLastName}
                                value={this.state.patientLastName}
                            />
                            <Box mt={3}></Box>
                            <TextField
                                id="dob"
                                label="DOB"
                                type="date"
                                onChange={this.updateDOB}
                                value={this.state.dob}
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
                                value={this.state.age}
                            />
                            <Box mt={3}></Box>
                            <TextField
                                id="gender"
                                label="Gender"
                                variant="outlined"
                                onChange={this.updateGender}
                                value={this.state.gender}
                            />
                            <Box mt={3}></Box>
                            <TextField
                                id="maritalStatus"
                                label="Marital Status"
                                variant="outlined"
                                onChange={this.updateMaritalStatus}
                                value={this.state.maritalStatus}
                            />
                            <Box mt={3}></Box>
                            <TextField
                                id="contactNo"
                                label="Mobile No"
                                variant="outlined"
                                onChange={this.updateContactNo}
                                value={this.state.contactNo}
                            />
                            <Box mt={3}></Box>
                            <TextField
                                id="emailId"
                                label="Email Id"
                                variant="outlined"
                                onChange={this.updateEmailId}
                                value={this.state.emailId}
                            />
                            <Box mt={3}></Box>
                            <TextField
                                id="address"
                                label="Address"
                                variant="outlined"
                                onChange={this.updateAddress}
                                value={this.state.address}
                            />
                            <Box mt={3}></Box>
                            <TextField
                                id="city"
                                label="City"
                                variant="outlined"
                                onChange={this.updateCity}
                                value={this.state.city}
                            />
                            <Box mt={3}></Box>
                            <TextField
                                id="country"
                                label="Country"
                                variant="outlined"
                                onChange={this.updateCountry}
                                value={this.state.country}
                            />
                            <Box mt={3}></Box>
                            <TextField
                                id="postalCode"
                                label="Postal Code"
                                variant="outlined"
                                onChange={this.updatePostalCode}
                                value={this.state.postalCode}
                            />
                            <Box mt={3}></Box>
                            <TextField
                                id="regDate"
                                label="Appointment Date"
                                type="date"
                                onChange={this.updateRegDate}
                                value={this.state.regDate}
                                InputLabelProps={{
                                    shrink: true,
                                }}
                            />
                        </div>
                        <Box mt={3}>
                            <Button
                                variant="contained"
                                color="primary"
                                onClick={this.onUpdate}
                                size="small"
                                startIcon={<SaveIcon />}>
                                Update Record
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