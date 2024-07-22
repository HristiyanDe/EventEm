import { AccountCircle } from "@mui/icons-material"
import { AppBar, Container, Box, MenuItem, Typography, Menu, IconButton, Button } from "@mui/material"
import MenuIcon from '@mui/icons-material/Menu';
import { useAuth } from "../../auth/AuthContext";
import React from "react";
import useMediaQuery from '@mui/material/useMediaQuery';
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
                    <MenuItem>
                    <Button href="/" fullWidth>Home</Button>
                    </MenuItem>
                    <MenuItem>
                    <Button href="/about-us" fullWidth>About Us</Button>
                    </MenuItem>
                    <MenuItem>
                    <Button href="/events" fullWidth>Events</Button>
                    </MenuItem>
                    <MenuItem>
                    <Button href="/partners" fullWidth>Partners</Button>
                    </MenuItem>
                </Menu>
                </Box>
            <Box sx={{ marginLeft: 1}}>
                <IconButton onClick={handleOpenUserMenu} sx={{position: "absolute", right:0}}>
                    <AccountCircle>

                    </AccountCircle>
                </IconButton>
                {//{token && userId ?}
}
                <Menu open = {userMenuIsOpen} anchorEl={anchorElUser} onClose={() => setAnchorElUser(null)} sx={{float: "right"}}>
                     
                    <MenuItem>
                    <Button href="/profile" fullWidth>Profile</Button>
                    </MenuItem>
                    <MenuItem>
                    <Button href="/logout" fullWidth>Logout</Button>
                    </MenuItem>
                </Menu>
                    {//:} 
}
                    <Menu open = {userMenuIsOpen} anchorEl={anchorElUser} onClose={() => setAnchorElUser(null)} sx={{float:"right"}}>
                    <MenuItem>
                    <Button href="/login" fullWidth>Login</Button>
                    </MenuItem>
                    <MenuItem>
                    <Button href="/register" fullWidth>Register</Button>
                    </MenuItem>
                </Menu>
                {//}
}
            </Box>
            </Box>
        </Container>
    </AppBar>
) :
(<Typography> Large screen </Typography>)
};
export default NavbarComponent;