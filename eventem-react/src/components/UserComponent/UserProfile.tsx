import React from "react";
import { useAuth } from "../../auth/AuthContext";
import { Typography, Box, CssBaseline, TextField, Icon, Button } from "@mui/material";
import  { ThemeProvider } from "@mui/material/styles";
import { Container } from "@mui/system";
import { defaultTheme } from '../RegisterComponent/RegisterComponent';
import { AccountCircle } from "@mui/icons-material";
import ModeEditIcon from '@mui/icons-material/ModeEdit';
import { useState } from "react";
import { UpdateUserRequest } from "../../models/UpdateUserRequest";
const UserProfileComponent: React.FC = () => {
    const { token, user} = useAuth();
    const [isEditing, setIsEditing] = useState(false);
    const handleEditClick = () => {
      setIsEditing(!isEditing);
  };
  const [formData, setFormData] = useState<UpdateUserRequest>({
    firstName: null,
    lastName: null,
    city: null,
    address: null,
    phone: null,
    email: null,
});

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const {name, value} = e.target;
    setFormData({
        ...formData,
        [name]:value,
    });
};
  return (
    <ThemeProvider theme={defaultTheme}>
          <Container component="main" maxWidth="xs">
            <CssBaseline />
            <Box sx={{
                marginTop: 8,
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',}}>
                    <Icon fontSize="large">
                    <AccountCircle fontSize="large"/>
                    </Icon>
                <Typography component="h1" variant="h5">
                    Profile
                </Typography>
                <Box sx={{marginTop: 3, marginBottom: 3, padding: 2, display: 'flex', flexDirection: 'column', alignItems: 'center'}}>
                <Button onClick={handleEditClick} id="edit-profile-button" variant="outlined" endIcon={<ModeEditIcon/>}> Button</Button>
                </Box>
                <Box component="form">
                    {/*<TextField sx={{paddingBottom: 2}} fullWidth id="firstName" label="First Name" variant="outlined" value={user?.username} />*/}
                    <TextField onChange={handleInputChange} disabled={!isEditing} sx={{paddingBottom: 2}} fullWidth id="firstName" label="First Name" variant="outlined" value={user?.firstName} />
                    <TextField onChange={handleInputChange} disabled={!isEditing} sx={{paddingBottom: 2}} fullWidth id="lastName" label="Last Name" variant="outlined" value={user?.lastName} />
                    <TextField onChange={handleInputChange} disabled={!isEditing} sx={{paddingBottom: 2}} fullWidth id="email" label="Email" variant="outlined" value={user?.email} />
                    <TextField onChange={handleInputChange} disabled={!isEditing} sx={{paddingBottom: 2}} fullWidth id="address" label="Address" variant="outlined" value={user?.address} />
                    <Button
                    id="submit-profile-button"
                  type="submit"
                  fullWidth
                  variant="contained"
                  sx={{ mt: 3, mb: 2 }}
                >
                  Edit profile
                </Button>
                </Box>
                <Box sx={{marginTop: 3, marginBottom: 3, padding: 2, display: 'flex', flexDirection: 'column', alignItems: 'center'}}>
                  <Button id="security-details">Security</Button>
                  </Box>
                </Box>
                
            </Container>
            </ThemeProvider>
);
};
export default UserProfileComponent;