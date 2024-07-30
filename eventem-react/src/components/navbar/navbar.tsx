import { AccountCircle } from "@mui/icons-material"
import { AppBar, Container, Box, MenuItem, Typography, Menu, IconButton, Button } from "@mui/material"
import MenuIcon from '@mui/icons-material/Menu';
import { useAuth } from "../../auth/AuthContext";
import React from "react";
import useMediaQuery from '@mui/material/useMediaQuery';
import  {NavigationLinks, LoggedOutLinks, LoggedInLinks} from "./navbarViewComponents";
const NavbarComponent: React.FC = () => {
    const {token,setToken, userId, setUser} = useAuth();
    const [anchorElUser, setAnchorElUser] = React.useState<null | HTMLElement>(null);
    const [anchorElNavbar, setAnchorElNavbar] = React.useState<null | HTMLElement>(null);
    const userMenuIsOpen = Boolean(anchorElUser);
    const navbarMenuIsOpen = Boolean(anchorElNavbar);
    const handleOpenUserMenu = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorElUser(event.currentTarget);
    }
    const isSmallScreen = useMediaQuery('(max-width:600px)');
    
return isSmallScreen ? (
    <AppBar position="sticky">
        <Container>
            <Box sx={{flexGrow: 1, zIndex: 999, display: {xs: 'flex', md:'flex'}, justifyContent: 'space-between'}}>
                <Box>
                <IconButton onClick={(event: React.MouseEvent<HTMLElement>) => setAnchorElNavbar(event.currentTarget)} sx={{position: "absolute", left:0}}>
                <MenuIcon />
                    </IconButton>
                <Menu open = {navbarMenuIsOpen} anchorEl={anchorElNavbar} onClose={() => setAnchorElNavbar(null)}>
                    <NavigationLinks/>
                </Menu>
                </Box>
            <Box sx={{ marginLeft: 1}}>
                <IconButton onClick={handleOpenUserMenu} sx={{position: "absolute", right:0}}>
                    <AccountCircle>

                    </AccountCircle>
                </IconButton>
                
                <Menu open = {userMenuIsOpen} anchorEl={anchorElUser} onClose={() => setAnchorElUser(null)} sx={{float: "right"}}>
                { token ? <LoggedOutLinks/> :  <LoggedInLinks/>}
                </Menu>
                    

            </Box>
            </Box>
        </Container>
    </AppBar>
) :
(
<AppBar sx={{marginLeft: 0, marginRight: 0, flexGrow: 1}}>
    <Container disableGutters={true} maxWidth={false} sx={{marginLeft: 0, marginRight: 0, flexGrow: 1}}>
        <Box sx={{ zIndex: 999, display: {xs: 'none', md:'flex'}, justifyContent: 'space-between', width: "100%", paddingRight: 0}}>
        <Box sx={{
    '& .MuiButton-root': {
        color: 'white',
    },
    display: 'inline-flex',
    alignItems: 'left',
    marginLeft: '0px',
    justifyContent: 'flex-start',
}}>
                <NavigationLinks/>
            </Box>
            <Box sx={{
    '& .MuiButton-root': {
        color: 'white',
    },
    display: 'flex',
    alignItems: 'right',
    marginLeft: '0px',
    justifyContent: 'flex-end',
}}>
              {!token ? <LoggedOutLinks/> :  <LoggedInLinks/>}
            </Box>
        </Box>
    </Container>
    </AppBar>
)
};
export default NavbarComponent;
