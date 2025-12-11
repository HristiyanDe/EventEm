import { Button, MenuItem, IconButton } from "@mui/material";
const AdminMenuNavigationLinks = function() {
    return (
        <>
    <MenuItem>
        <Button href="/user-management" fullWidth>User management</Button>
    </MenuItem>
        <MenuItem>
        <Button href="/analytics" fullWidth>Analytics</Button>
    </MenuItem>
    <MenuItem>
        <Button href="/create-event" fullWidth>Create Event</Button>
    </MenuItem>
     <MenuItem>
        <Button href="/create-organization" fullWidth>Create Organization</Button>
    </MenuItem>
         <MenuItem>
        <Button href="/create-category" fullWidth>Create Category</Button>
    </MenuItem>
    <MenuItem>
        <Button href="/create-venue" fullWidth>Create Venue</Button>
    </MenuItem>
    </>
    );
}
export { AdminMenuNavigationLinks };