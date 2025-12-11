import React from 'react';
import { ThemeProvider } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import SearchIcon from '@mui/icons-material/Search';
import { defaultTheme } from '../RegisterComponent/RegisterComponent';
import { TextField } from '@mui/material';
import { InputAdornment } from '@mui/material';
import { userService } from '../../api/userService';
import { useAuth } from '../../auth/AuthContext';
const UserManagementComponent: React.FC = () => {
const { token, setToken, user,setUser } = useAuth();
    const [input, setInput] = React.useState<string>('');
    const handleInputChange = (value: string) => {
        setInput(value);
        fetchUsers(value);
    };
    const fetchUsers = (value: string) => {
        // Fetch users based on the input value
        userService.searchUsers(value,token).then(users => {
            // Handle the fetched users
            console.log(users);
        }).catch(error => {
            console.error('Error fetching users:', error);
        });
    };
    return (
<ThemeProvider theme={defaultTheme}>
    <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
              sx={{
                marginTop: 8,
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
              }}
        >
            <Box component="form" noValidate sx={{ mt: 1 ,
                display: 'flex',
                flexDirection: 'row',
                alignItems: 'center',
            }}>
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
              <SearchIcon/>
            </InputAdornment>
            ),
          }}
                />
            </Box>
        </Box>
          </Container>
    </ThemeProvider>
    );
}
export default UserManagementComponent;