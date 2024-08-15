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
import {userService} from "../../api/userService";
import Cookies from "js-cookie";
import { useEffect } from "react";
const UserProfileComponent: React.FC = () => {
    const { token, user} = useAuth();
    const [isEditing, setIsEditing] = useState(false);
    const handleEditClick = () => {
      setIsEditing(!isEditing);
  };
  const [formData, setFormData] = useState<UpdateUserRequest>({
    firstName: user?.firstName || "",
    lastName: user?.lastName || "",
    city: user?.city || "",
    address: user?.address || "",
    phone: user?.phone || "",
    email: user?.email || "",
});
const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
  e.preventDefault();
  if (token && user) {
    console.log("User Id: "+user.id);
    console.log("User: "+JSON.stringify(user));
    const updatedUser = userService.updateUserProfile( formData, token, user.id);
    Cookies.set('user', JSON.stringify(updatedUser));
  }

  console.log(formData);
};

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const {name, value} = e.target;
    setFormData({
        ...formData,
        [name]:value,
    });
};
useEffect(() => {
  if (user) {
    setFormData({
      firstName: user.firstName,
      lastName: user.lastName,
      city: user.city,
      address: user.address,
      phone: user.phone,
      email: user.email,
    });
  }
}, [user]);
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
                <Box component="form" onSubmit={handleSubmit}>
                    {/*<TextField sx={{paddingBottom: 2}} fullWidth id="firstName" label="First Name" variant="outlined" value={user?.username} />*/}
                    <TextField onChange={handleInputChange} disabled={!isEditing} sx={{paddingBottom: 2}} fullWidth id="firstName" name="firstName" label="First Name" variant="outlined" value={formData.firstName} />
                    <TextField onChange={handleInputChange} disabled={!isEditing} sx={{paddingBottom: 2}} fullWidth id="lastName" name="lastName" label="Last Name" variant="outlined" value={formData.lastName} />
                    <TextField onChange={handleInputChange} disabled={!isEditing} sx={{paddingBottom: 2}} fullWidth id="city" name="city" label="City" variant="outlined" value={formData.city} />
                    <TextField onChange={handleInputChange} disabled={!isEditing} sx={{paddingBottom: 2}} fullWidth id="address" name="address" label="Address" variant="outlined" value={formData.address} />
                    <TextField onChange={handleInputChange} disabled={!isEditing} sx={{paddingBottom: 2}} fullWidth id="phone" name="phone" label="phone" variant="outlined" value={formData.phone} />
                    <TextField onChange={handleInputChange} disabled={!isEditing} sx={{paddingBottom: 2}} fullWidth id="email" name="email" label="Email" variant="outlined" value={formData.email} />
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