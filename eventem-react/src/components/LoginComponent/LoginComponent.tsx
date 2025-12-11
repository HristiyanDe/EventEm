import { LoginRequest } from "../../models/LoginRequest";
import axios from "axios";
import { API_LOGIN_PATH } from "../../constants/apiConstants";
import { useState } from "react";
import { useAuth, useLogout } from "../../auth/AuthContext";
import React from 'react';
import { ThemeProvider } from '@mui/material/styles';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { defaultTheme } from '../RegisterComponent/RegisterComponent';
import { Navigate } from "react-router-dom";
import Cookies from 'js-cookie';
import { Link as RouterLink } from "react-router-dom";
const LoginComponent: React.FC = () => {
    const { token, setToken, user,setUser } = useAuth();
    const  logout  = useLogout();
    const [formData, setFormData] = useState<LoginRequest>({
        username: '',
        password: '',
    });
    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        try {
            if (token && user) {
                return <Navigate to="/"/>;
            }
            const response = await axios.post(API_LOGIN_PATH, formData);
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
    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const {name, value} = e.target;
        setFormData({
            ...formData,
            [name]:value,
        });
    };
    if(token && user)
      {
        
        console.log(token,user);
        return <Navigate to="/"/>
      }
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
                Sign in
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
                  name="password"
                  label="Password"
                  type="password"
                  id="password"
                  autoComplete="current-password"
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
                <Grid container>
                  <Grid item xs>
                    <Link component={RouterLink} to="/reset-password" variant="body2">
                      Forgot password?
                    </Link>
                  </Grid>
                  <Grid item>
                     <Link component={RouterLink} to="/register" variant="body2">
                      Don't have an account? Sign Up
                    </Link>
                  </Grid>
                </Grid>
              </Box>
            </Box>
          </Container>
        </ThemeProvider>
      );
            };
export default LoginComponent;