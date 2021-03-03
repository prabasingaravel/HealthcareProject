import React, { Component } from 'react';
import { Container, Typography, Box } from '@material-ui/core';
import UserService from '../../services/UserService';
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
import TextField from '@material-ui/core/TextField';
import ArrowBackIcon from '@material-ui/icons/ArrowBack';

class UserListComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            users: []
        }

        this.updateUser = this.updateUser.bind(this);
        this.deleteUser = this.deleteUser.bind(this);
    }

    componentDidMount() {
        UserService.getAllUser().then((res) => {
            this.setState({ users: res.data });
        })
    }

    updateUser(id) {
        this.props.history.push(`/update-user/${id}`);
    }

    deleteUser(id) {
        UserService.deleteUser(id).then((res) => {
            this.componentDidMount();
        });
    }

    searchInput = (event) => {
        this.setState({ searchInput: event.target.value });
    }

    render() {
        return (
            
            <Container>
                <Box mt={5}></Box>
                <Container maxWidth="sm">
                    <Button
                        variant="contained"
                        color="default"
                        href="http://localhost:3000/"
                        size="small"
                        startIcon={<ArrowBackIcon />}>
                        Back
                        </Button>
                </Container>
                <Box mt={5}>
                </Box>
                <Container maxWidth="sm">
                    <Typography variant="h3" gutterBottom>
                        User List
                </Typography>
                    <Box mt={5}>
                        <div className="addIcon">
                            <Tooltip title="Add" aria-label="add">
                                <Fab color="primary" aria-label="add" href="http://localhost:3000/add-user">
                                    <AddIcon />
                                </Fab>
                            </Tooltip>
                        </div>
                    </Box>
                    <Box mt={5}>
                        <TableContainer component={Paper}>
                            <Table aria-label="simple table">
                                <TableHead>
                                    <TableRow>
                                        <TableCell>User ID</TableCell>
                                        <TableCell align="right">User Name</TableCell>
                                        <TableCell align="right">Role ID</TableCell>
                                        <TableCell align="right">Actions</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {this.state.users.map((user) => (
                                        <TableRow key={user.userId}>
                                            <TableCell align="right">{user.userId}</TableCell>
                                            <TableCell align="right">{user.userName}</TableCell>
                                            <TableCell align="right">{user.roleId}</TableCell>
                                            <TableCell align="right">
                                                <Button
                                                    variant="contained"
                                                    color="primary"
                                                    size="small"
                                                    startIcon={<EditIcon />}
                                                    onClick={() => this.updateUser(user.userId)}>
                                                </Button>
                                                <Button
                                                    variant="contained"
                                                    color="secondary"
                                                    size="small"
                                                    startIcon={<DeleteIcon />}
                                                    onClick={() => this.deleteUser(user.userId)}>
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

export default UserListComponent;