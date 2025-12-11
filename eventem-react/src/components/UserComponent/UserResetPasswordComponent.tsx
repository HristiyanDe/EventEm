import axios from "axios";
import { useState } from "react";
import React from 'react';
import { ThemeProvider } from '@mui/material/styles';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { defaultTheme } from '../RegisterComponent/RegisterComponent';
import { Navigate } from "react-router-dom";
import Cookies from 'js-cookie';
import { ResetPasswordRequest } from "../../models/ResetPasswordRequest";
import { useAuth } from "../../auth/AuthContext";
import { API_RESET_PASSWORD_PATH } from "../../constants/apiConstants";
const UserProfileSecurityComponent: React.FC = () => {
    const { token, setToken, user,setUser } = useAuth();
    const email = '';
    const [formData, setFormData] = useState<ResetPasswordRequest>({
        username: '',
        password: '',
        confirmPassword: '',
        });
    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
            const {name, value} = e.target;
            setFormData({
                ...formData,
                [name]:value,
            });
        };
        const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
            event.preventDefault();
            try {
                if (token && user) {
                    return <Navigate to="/"/>;
                }
                const response = await axios.post(API_RESET_PASSWORD_PATH, formData);
                if (response.status !== 200) {
                    throw new Error('Failed to login');
                }
                setToken(response.data.token);
                setUser(response.data.user);
                Cookies.set('token', response.data.token);
                Cookies.set('user', JSON.stringify(response.data.user));
            }
            catch (error) {
                console.error(error);
            }
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
              <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                <LockOutlinedIcon />
              </Avatar>
              <Typography component="h1" variant="h5">
                Reset password
              </Typography>
              <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
                <TextField
                  margin="normal"
                  required
                  fullWidth
                  id="username"
                  label="Username"
                  name="username"
                  autoComplete="username"
                  autoFocus
                  onChange={handleInputChange}
                />
                <TextField
                  margin="normal"
                  required
                  fullWidth
                  id="password"
                  label="New Password"
                  name="password"
                  autoComplete="current-password"
                  autoFocus
                  onChange={handleInputChange}
                />
                <TextField
                  margin="normal"
                  required
                  fullWidth
                  id="confirmPassword"
                  label="Confirm Password"
                  name="confirmPassword"
                  autoComplete="current-password"
                  autoFocus
                  onChange={handleInputChange}
                />
                <Button
                  type="submit"
                  fullWidth
                  variant="contained"
                  sx={{ mt: 3, mb: 2 }}
                >
                  Sign In
                </Button>
              </Box>
            </Box>
          </Container>
        </ThemeProvider>
      );
}
export default UserProfileSecurityComponent;