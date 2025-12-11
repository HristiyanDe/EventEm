import { Container, TextField, Button, CssBaseline, Box, Icon } from "@mui/material";
import { useState } from "react";
import { useAuth } from "../../auth/AuthContext";
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import {userService} from "../../api/userService";
import Cookies from "js-cookie";
import { ThemeProvider } from "@mui/material/styles";
import { defaultTheme } from '../RegisterComponent/RegisterComponent';
import { UpdateUserSecurityRequest } from "../../models/UpdateUserSecurityRequest";

const UserProfileSecurityComponent: React.FC = () => {
    const { token, setToken, user, setUser} = useAuth();
    const [formData, setFormData] = useState<UpdateUserSecurityRequest>({
        username: '',
        password: '',
        newPassword: '',
    })
    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const {name, value} = e.target;
        setFormData({
            ...formData,
            [name]:value,
        });
    };
    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        if (token && user) {
          console.log("Component log: ID: "+user.id)
          console.log("User Id: "+user.id);
          console.log("User: "+JSON.stringify(user));
          const newToken = await userService.updateUserSecurity( formData, token, user.id as string);
          Cookies.set('token', JSON.stringify(newToken));
          setToken(newToken);
        }
    }
    return (
        <ThemeProvider theme={defaultTheme}>
          <Container component="main" maxWidth="xs">
            <CssBaseline/>
            <Box sx={{
                marginTop: 8,
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',}}>
                    <Icon fontSize="large">
                    <LockOutlinedIcon fontSize="large"/>
                    </Icon>
            <Box component="form" onSubmit={handleSubmit} noValidate sx={{mt: 1}}>
            <TextField onChange={handleInputChange}  sx={{paddingBottom: 2}} fullWidth id="username" name="username" label="Username" variant="outlined" value={formData.username} />
            <TextField onChange={handleInputChange}  sx={{paddingBottom: 2}} fullWidth id="password" name="password" label="Password" variant="outlined" value={formData.password} />
            <TextField onChange={handleInputChange}  sx={{paddingBottom: 2}} fullWidth id="newPassword" name="newPassword" label="New Password" variant="outlined" value={formData.newPassword} />
            <Button
                    id="submit-profile-button"
                  type="submit"
                  fullWidth
                  variant="contained"
                  sx={{ mt: 3, mb: 2 }}
                >
                  Save
                </Button>
            </Box>
            </Box>
        </Container>
        </ThemeProvider>
    );
}
export default UserProfileSecurityComponent;