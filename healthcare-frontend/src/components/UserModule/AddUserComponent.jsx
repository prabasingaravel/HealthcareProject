import React, { Component } from 'react';
import TextField from '@material-ui/core/TextField';
import { Container, Box } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import SaveIcon from '@material-ui/icons/Save';
import Button from '@material-ui/core/Button';
import UserService from '../../services/UserService';

class AddUserComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            userName: '',
            password: '',
            roleId: ''
        }

        this.updateUserName = this.updateUserName.bind(this);
        this.updatePassword = this.updatePassword.bind(this);
        this.updateRoleId = this.updateRoleId.bind(this);
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

    updateUserName = (event) => {
        this.setState({ userName: event.target.value });
    }

    updatePassword = (event) => {
        this.setState({ password: event.target.value });
    }

    updateRoleId = (event) => {
        this.setState({ roleId: event.target.value });
    }

    onSave = (event) => {
        let users = {
            userName: this.state.userName,
            password: this.state.password,
            roleId: this.state.roleId
        }
        if((users.userName !== undefined && users.userName !== null && users.userName !== "") &&
            (users.password !== undefined && users.password !== null && users.password !== "") &&
            (users.roleId !== undefined && users.roleId !== null && users.roleId !== "")){
            UserService.addUser(users).then(res => {
                this.props.history.push("/users");
            });
        }
    
    }

    render() {
        return (
            <Container maxWidth="sm">
                <div>
                    <Box mt={5}></Box>
                    <form className={this.classes.root} noValidate autoComplete="off">
                        <div>
                            <TextField
                                required
                                id="userName"
                                label="User Name"
                                defaultValue=""
                                variant="outlined"
                                onChange={this.updateUserName}
                            />
                            <Box mt={5}></Box>
                            <TextField
                                required
                                id="password"
                                label="Password"
                                type="password"
                                autoComplete="current-password"
                                variant="outlined"
                                onChange={this.updatePassword}
                            />
                            <Box mt={5}></Box>
                            <TextField
                                required
                                id="roleId"
                                label="Role Id"
                                defaultValue=""
                                variant="outlined"
                                onChange={this.updateRoleId}
                            />
                        </div>
                        <Box mt={5}>
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
                                href="http://localhost:3000/users">
                                Cancel Record
                        </Button>
                        </Box>
                    </form>
                </div>
            </Container>
        );
    }
}



export default AddUserComponent;