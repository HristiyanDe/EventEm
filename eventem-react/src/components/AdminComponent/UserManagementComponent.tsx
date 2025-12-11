import React from 'react';
import { Navigate } from 'react-router-dom';
import { ThemeProvider } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import SearchIcon from '@mui/icons-material/Search';
import { defaultTheme } from '../RegisterComponent/RegisterComponent';
import {
    TextField,
    Typography,
    InputAdornment,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Paper, 
    Button, 
    Menu,
    MenuItem
} from '@mui/material';
import { userService } from '../../api/userService';
import { useAuth } from '../../auth/AuthContext';

const UserManagementComponent: React.FC = () => {
    const [anchorElRoles, setAnchorElRoles] = React.useState<null | HTMLElement>(null);
    const [roles, setRoles] = React.useState<string[]>([]);
    const rolesMenuIsOpen = Boolean(anchorElRoles);
    const { token, setToken, user, setUser } = useAuth();
    const [input, setInput] = React.useState<string>('');
    const [userList, setUserList] = React.useState<any[]>([]);
        const handleOpenRolesMenu = async (event: React.MouseEvent<HTMLElement>) => {
            setAnchorElRoles(event.currentTarget);
            await fetchUserRoles();
        }

    const handleInputChange = (value: string) => {
        setInput(value);
        fetchUsers(value);
        console.log(userList);
    };
    const fetchUserRoles = async () => {
        const roles = await userService.getAllUserRoles(token);
        setRoles(roles);
        console.log(roles);
    };
    const fetchUsers = (value: string) => {
        userService.searchUsers(value, token).then(users => {
            setUserList(users);
        }).catch(error => {
            console.error('Error fetching users:', error);
        });
    };

    const handleBanButtonClick = async (username: string) => {
        await userService.banUser(username, token);
        fetchUsers(input);
    };
    const handleUpdateUserRoleButtonClick = async (username: string, role: string) => {
        await userService.updateUserRole(username, role, token);
        fetchUsers(input);
    };

    return (
        <ThemeProvider theme={defaultTheme}>
            <Container component="main" maxWidth="md">
                <CssBaseline />
                <Box
                    sx={{
                        marginTop: 8,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                        width: '100%',
                    }}
                >
                    <Box component="form" noValidate sx={{ mt: 1, mb: 3, width: '100%' }}>
                        <TextField
                            onChange={(e) => handleInputChange(e.target.value)}
                            margin="normal"
                            required
                            fullWidth
                            id="search-user"
                            label="Search user by username or email"
                            name="searchUser"
                            autoComplete="search-user"
                            autoFocus
                            InputProps={{
                                startAdornment: (
                                    <InputAdornment position="start">
                                        <SearchIcon />
                                    </InputAdornment>
                                ),
                            }}
                        />
                    </Box>

                    {userList.length > 0 && (
                        <TableContainer component={Paper}>
                            <Table sx={{ minWidth: 650 }} aria-label="user management table">
                                <TableHead>
                                    <TableRow>
                                        <TableCell>Username</TableCell>
                                        <TableCell align="right">Role</TableCell>
                                        <TableCell align="right">Enabled</TableCell>
                                        <TableCell align="center">Actions</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {userList.map((userItem) => (
                                        <TableRow
                                            key={userItem.username}
                                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                        >
                                            <TableCell component="th" scope="row">
                                                {userItem.username}
                                            </TableCell>
                                            <TableCell align="right">{userItem.role}</TableCell>
                                            <TableCell align="right">{String(userItem.enabled)}</TableCell>
                                            <TableCell align="center">
                                                <Button
                                                    variant="contained"
                                                    size="small"
                                                    style={{ backgroundColor: userItem.enabled ? 'red' : 'green' }}
                                                    onClick={() => handleBanButtonClick(userItem.username)}
                                                >
                                                    {userItem.enabled ? "Ban" : "Unban"}
                                                </Button>
                                                <Button
                                                    variant="contained"
                                                    size="small"
                                                    style={{ marginLeft: '10px' }}
                                                    onClick={handleOpenRolesMenu}>
                                                    Update Role
                                                </Button>
                                                 <Menu open = {rolesMenuIsOpen} anchorEl={anchorElRoles} onClose={() => setAnchorElRoles(null)} sx={{float: "right"}}>
                                                    {roles.map((roleItem) => (
                                                        <MenuItem key={roleItem} onClick={() => handleUpdateUserRoleButtonClick(userItem.username, roleItem)}>
                                                            {roleItem}
                                                        </MenuItem>
                                                    ))}
                                                 </Menu>
                                            </TableCell>
                                        </TableRow>
                                    ))}
                                </TableBody>
                            </Table>
                        </TableContainer>
                    )}
                    {userList.length === 0 && input !== '' && (
                        <Typography sx={{ mt: 2 }}>No users found matching your search criteria.</Typography>
                    )}

                </Box>
            </Container>
        </ThemeProvider>
    );
}

export default UserManagementComponent;