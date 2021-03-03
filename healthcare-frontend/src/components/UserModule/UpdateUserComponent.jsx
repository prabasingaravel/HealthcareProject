import React, { Component } from 'react';
import TextField from '@material-ui/core/TextField';
import { Container, Box } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import SaveIcon from '@material-ui/icons/Save';
import Button from '@material-ui/core/Button';
import UserService from '../../services/UserService';

class UpdateUserComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            userId: this.props.match.params.id,
            userName: "",
            password: "",
            roleId: ""
        }

        this.updateUserName = this.updateUserName.bind(this);
        this.updatePassword = this.updatePassword.bind(this);
        this.updateRoleId = this.updateRoleId.bind(this);
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

    updateUserName = (event) => {
        this.setState({ userName: event.target.value });
    }

    updatePassword = (event) => {
        this.setState({ password: event.target.value });
    }

    updateRoleId = (event) => {
        this.setState({ roleId: event.target.value });
    }

    onUpdate = (event) => {
        let users = {
            userId: parseInt(this.props.match.params.id),
            userName: this.state.userName,
            password: this.state.password,
            roleId: parseInt(this.state.roleId)
        }
        if ((users.userName !== undefined && users.userName !== null && users.userName !== "") &&
            (users.password !== undefined && users.password !== null && users.password !== "") &&
            (users.roleId !== undefined && users.roleId !== null && users.roleId !== "")) {
            console.log(users);
            UserService.updateUser(users).then(res => {
                this.props.history.push("/users");
            });
        }

    }

    componentDidMount() {
        UserService.getUserById(this.state.userId).then((res) => {
            let users = res.data;
            this.setState({ userName: users.userName });
            if(users.password !== undefined && users.password !== null && users.password !== ""){
                this.setState({ password: users.password });
            } else {
                this.setState({ password: "" });
            }
            this.setState({ roleId: users.roleId });
        });
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
                                variant="outlined"
                                onChange={this.updateUserName}
                                value={this.state.userName}>
                            </TextField>
                            <Box mt={5}></Box>
                            <TextField
                                required
                                id="password"
                                label="Password"
                                type="password"
                                variant="outlined"
                                onChange={this.updatePassword}
                                value={this.state.password}>
                            </TextField>
                            <Box mt={5}></Box>
                            <TextField
                                required
                                id="roleId"
                                label="Role Id"
                                variant="outlined"
                                onChange={this.updateRoleId}
                                value={this.state.roleId}>
                            </TextField>
                        </div>
                        <Box mt={5}>
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

export default UpdateUserComponent;