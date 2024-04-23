import { AccountCircle } from "@mui/icons-material"
import { AppBar, Container, Box, MenuItem, Typography, Menu, IconButton } from "@mui/material"
import MenuIcon from '@mui/icons-material/Menu';
import { useAuth } from "../../auth/AuthContext";
import React from "react";
const NavbarComponent: React.FC = () => {
    const {token,setToken, userId, setUser} = useAuth();
    const [anchorElUser, setAnchorElUser] = React.useState<null | HTMLElement>(null);
    const [anchorElNavbar, setAnchorElNavbar] = React.useState<null | HTMLElement>(null);
    const userMenuIsOpen = Boolean(anchorElUser);
    const navbarMenuIsOpen = Boolean(anchorElNavbar);
    const handleOpenUserMenu = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorElUser(event.currentTarget);
    }
    
return (
    <AppBar position="sticky">
        <Container>
            <Box sx={{flexGrow: 1, zIndex: 999, display: {xs: 'flex', md:'flex'}, justifyContent: 'space-between'}}>
                <Box>
                <IconButton onClick={(event: React.MouseEvent<HTMLElement>) => setAnchorElNavbar(event.currentTarget)} sx={{position: "absolute", left:0}}>
                <MenuIcon />
                    </IconButton>
                <Menu open = {navbarMenuIsOpen} anchorEl={anchorElNavbar} onClose={() => setAnchorElNavbar(null)}>
                    <MenuItem>
                    <Typography textAlign="center">Home</Typography>
                    </MenuItem>
                    <MenuItem>
                    <Typography textAlign="center">About</Typography>
                    </MenuItem>
                    <MenuItem>
                    <Typography textAlign="center">Events</Typography>
                    </MenuItem>
                    <MenuItem>
                    <Typography textAlign="center">Partners</Typography>
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
                    <Typography>Profile</Typography>
                    </MenuItem>
                    <MenuItem>
                    <Typography>Logout</Typography>
                    </MenuItem>
                </Menu>
                    {//:} 
}
                    <Menu open = {userMenuIsOpen} anchorEl={anchorElUser} onClose={() => setAnchorElUser(null)} sx={{float:"right"}}>
                    <MenuItem>
                    <Typography>Login</Typography>
                    </MenuItem>
                    <MenuItem>
                    <Typography>Register</Typography>
                    </MenuItem>
                </Menu>
                {//}
}
            </Box>
            </Box>
        </Container>
    </AppBar>
)
};
export default NavbarComponent;