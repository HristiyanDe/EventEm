import { Button, MenuItem } from "@mui/material";
import { useLogout } from "../../auth/AuthContext"
    const NavigationLinks = function(){
        return (
                <>
                <MenuItem>
                <Button href="/" fullWidth>Home</Button>
            </MenuItem><MenuItem>
                    <Button href="/about-us" fullWidth>About Us</Button>
                </MenuItem><MenuItem>
                    <Button href="/events" fullWidth>Events</Button>
                </MenuItem><MenuItem>
                    <Button href="/partners" fullWidth>Partners</Button>
                </MenuItem>
                </>
           
        )
    } 
    export {NavigationLinks};

    const LoggedOutLinks = function(){
        return (
            <>
                <MenuItem>
                    <Button href="/login" fullWidth>Login</Button>
                </MenuItem>
                <MenuItem>
                    <Button href="/register" fullWidth>Register</Button>
                </MenuItem>
            </>
        )
    }
    export {LoggedOutLinks};
    
    const LoggedInLinks = () =>{
        const logout = useLogout();
        return (
            <>
                <MenuItem>
                    <Button href="/profile" fullWidth>Profile</Button>
                </MenuItem>
                <MenuItem>
                    <Button onClick={logout} href="/logout" fullWidth>Logout</Button>
                </MenuItem>
                <MenuItem>
                    <Button href="/admin" fullWidth>Admin menu</Button>
                </MenuItem>
            </>
        )
    }
    export {LoggedInLinks};
