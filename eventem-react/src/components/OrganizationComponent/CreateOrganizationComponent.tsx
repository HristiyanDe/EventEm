
import React, { useState } from 'react';
import {API_VENUES_PATH} from '../../constants/apiConstants';
import axios from 'axios';
import { useAuth } from '../../auth/AuthContext';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import CorporateFareIcon from '@mui/icons-material/CorporateFare';
import { ThemeProvider } from '@mui/material/styles';
import { defaultTheme } from '../RegisterComponent/RegisterComponent';
import { OrganizationRequest } from '../../models/OrganizationRequest';
import { organizationService } from '../../api/organizationService';
import { Navigate } from 'react-router-dom';
const CreateOrganizationComponent: React.FC = () => {
    const [formData, setFormData] = useState<OrganizationRequest>({
    name: '',
    city: '',
    address: '',
    phone: '',
    email: '',
    });
    const { token, user } = useAuth();
const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    try {
        console.log(token);
        const location = organizationService.createOrganizationComponent(token, formData);
        console.log(location);
    } catch (error) {
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
if (!token || !user) {
  return <Navigate to= "/login"/>
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
            <CorporateFareIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Add an Organization
          </Typography>
          <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
            <TextField
              margin="normal"
              required
              fullWidth
              id="create-organization-name"
              label="Organization name"
              name="name"
              autoComplete="name"
              autoFocus
              onChange={handleInputChange}
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="city"
              label="Organization city"
              id="create-organization-city"
              autoComplete="city"
              onChange={handleInputChange}
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="address"
              label="Organization Address"
              id="create-organization-address"
              autoComplete="organization address"
              onChange={handleInputChange}
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="phone"
              label="Organization phone"
              id="create-organization-phone"
              autoComplete="organization phone"
              onChange={handleInputChange}
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="email"
              label="Organization email"
              id="create-organization-email"
              autoComplete="organization email"
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
                <Link href="#" variant="body2">
                  Forgot password?
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
        };
        export default CreateOrganizationComponent;