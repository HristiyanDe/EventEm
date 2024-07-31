import React from "react";
import { useAuth } from "../../auth/AuthContext";
import { Typography, Box, CssBaseline } from "@mui/material";
import  { ThemeProvider } from "@mui/material/styles";
import { Container } from "@mui/system";
import { defaultTheme } from '../RegisterComponent/RegisterComponent';
const UserProfileComponent: React.FC = () => {
    const { token, user} = useAuth();
  return (
    <ThemeProvider theme={defaultTheme}>
          <Container component="main" maxWidth="xs">
            <CssBaseline />
            <Box sx={{
                marginTop: 8,
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',}}>
                <Typography component="h1" variant="h5">
                    User Profile
                </Typography>
                <Typography component="h1" variant="h5">
                    {user?.firstName}
                </Typography>
                <Typography component="h1" variant="h5">
                    {user?.email}
                </Typography>
                <Typography component="h1" variant="h5">
                    {user?.address}
                </Typography>
            </Box>
            </Container>
            </ThemeProvider>
);
};
export default UserProfileComponent;