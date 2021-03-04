import React, { Component } from 'react';
import { Container, Typography, Box } from '@material-ui/core';
import PatientService from '../../services/PatientService';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import Fab from '@material-ui/core/Fab';
import AddIcon from '@material-ui/icons/Add';
import Tooltip from '@material-ui/core/Tooltip';
import Button from '@material-ui/core/Button';
import EditIcon from '@material-ui/icons/Edit';
import DeleteIcon from '@material-ui/icons/Delete';
import ArrowBackIcon from '@material-ui/icons/ArrowBack';

class PatientListComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            patients: []
        }

        this.updatePatient = this.updatePatient.bind(this);
        this.deletePatient = this.deletePatient.bind(this);
    }

    componentDidMount() {
        PatientService.getAllPatients().then((res) => {
            this.setState({ patients: res.data });
        })
    }

    updatePatient(id) {
        this.props.history.push(`/update-patient/${id}`);
    }

    deletePatient(id) {
        PatientService.deletePatient(id).then((res) => {
            this.componentDidMount();
        });
    }

    render() {
        return (
            
            <Container>
                <Box mt={5}></Box>
                <Container>
                    <Button
                        variant="contained"
                        color="default"
                        href="http://localhost:3000/"
                        size="small"
                        startIcon={<ArrowBackIcon />}>
                        Back
                        </Button>
                </Container>
                <Container maxWidth="md">
                    <Typography variant="h3" gutterBottom>
                        Patient List
                </Typography>
                        <div className="addIcon">
                            <Tooltip title="Add" aria-label="add">
                                <Fab color="primary" aria-label="add" href="http://localhost:3000/add-patient">
                                    <AddIcon />
                                </Fab>
                            </Tooltip>
                        </div>
                    <Box mt={3}>
                        <TableContainer component={Paper}>
                            <Table aria-label="simple table">
                                <TableHead>
                                    <TableRow>
                                        <TableCell align="center">Patient ID</TableCell>
                                        <TableCell align="center">First Name</TableCell>
                                        <TableCell align="center">Last Name</TableCell>
                                        <TableCell align="center">Age</TableCell>
                                        <TableCell align="center">Gender</TableCell>
                                        <TableCell align="center">Mobile Number</TableCell>
                                        <TableCell align="center">Actions</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {this.state.patients.map((patient) => (
                                        <TableRow key={patient.patientId}>
                                            <TableCell align="right">{patient.patientId}</TableCell>
                                            <TableCell align="left">{patient.patientFirstName}</TableCell>
                                            <TableCell align="left">{patient.patientLastName}</TableCell>
                                            <TableCell align="right">{patient.age}</TableCell>
                                            <TableCell align="left">{patient.gender}</TableCell>
                                            <TableCell align="right">{patient.contactNo}</TableCell>
                                            <TableCell align="right">
                                                <Button
                                                    variant="contained"
                                                    color="primary"
                                                    size="small"
                                                    startIcon={<EditIcon />}
                                                    onClick={() => this.updatePatient(patient.patientId)}>
                                                </Button>
                                                <Button
                                                    variant="contained"
                                                    color="secondary"
                                                    size="small"
                                                    startIcon={<DeleteIcon />}
                                                    onClick={() => this.deletePatient(patient.patientId)}>
                                                </Button>
                                            </TableCell>
                                        </TableRow>
                                    ))}
                                </TableBody>
                            </Table>
                        </TableContainer>
                    </Box>
                </Container>
            </Container>
        );

    }
}

export default PatientListComponent;