import React, { useEffect, useState } from "react";
import { Venue } from "../../models/dtos/VenueDTO";
import { ThemeProvider } from "@emotion/react";
import { defaultTheme } from "../RegisterComponent/RegisterComponent";
import { List, MenuItem, Select, Box, Container, CssBaseline, Typography, Grid, TextField, Button, ListItem, ListItemText, ListItemButton } from "@mui/material";
import { venueService } from "../../api/venueService";
import { SelectChangeEvent } from "@mui/material";
const SelectVenueComponent: React.FC = () => {
    const [formData, setFormData] = useState<Venue>({
        id: null,
        name: '',
        city: '',
        address: '',

    });

    //Generate a handleVenueSelect function which sets selectedVenue to the value provided by the <Select> component;
    // const handleVenueSelect = (event: React.MouseEvent<Venue>) => {
    //     setSelectedVenue(event.target. as Venue);
    //     console.log(event.target.value);
    // }
    const handleListItemClick = (
        event: React.MouseEvent<HTMLDivElement, MouseEvent>,
        id: number,
      ) => {
        if(id !== null){
            setSelectedVenue(venues.find(venue => venue.id === id) || null);
           
        }
        
      };
    

    const [selectedVenue, setSelectedVenue] = useState<Venue | null>(null);
    const [venues, setVenues] = useState<Venue[]>([]);
    // async function getVenuesSubmit(){
    //     const response = await venueService.getVenuesFiltered(formData.name,formData.address,formData.city);
    //     setVenues(response);
    // }
    const getVenuesSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        venueService.getVenuesFiltered(formData.name, formData.address, formData.city)
            .then(response => {
                setVenues(response);
            })
            .catch(error => {
                console.error(error);
            });
    };
    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const {name, value} = e.target;
        setFormData({
            ...formData,
            [name]:value,
        });
    };
    const handleVenueSubmit =(e: React.FormEvent<HTMLFormElement>)=> {
        e.preventDefault();
        // Perform any necessary actions with the selectedVenue
        console.log(selectedVenue);
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
                        alignItems: 'center'
                    }}
                >
                    <Typography component="h1" variant="h5">
                        Filter Venues by name,city and address.
                    </Typography>
                    <Box component="form" noValidate onSubmit={getVenuesSubmit} sx={{ mt: 3 }}>
                        <Grid container spacing={2}>
                            <Grid item xs={12}>
                                <TextField
                                    autoComplete="name"
                                    name="name"
                                    required
                                    fullWidth
                                    id="get-venues-name"
                                    label="Venue name"
                                    autoFocus
                                    value={formData.name}
                                    onChange={handleInputChange}
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <TextField
                                    autoComplete="city"
                                    name="city"
                                    required
                                    fullWidth
                                    id="get-venues-city"
                                    label="City name"
                                    autoFocus
                                    value={formData.city}
                                    onChange={handleInputChange}
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <TextField
                                    autoComplete="address"
                                    name="address"
                                    required
                                    fullWidth
                                    id="get-venues-address"
                                    label="Venue address"
                                    autoFocus
                                    value={formData.address}
                                    onChange={handleInputChange}
                                />
                            </Grid>
                        </Grid>
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{ mt: 3, mb: 2 }}
                        >Filter venues</Button>
                    </Box>
                    <Box component="form" noValidate onSubmit={handleVenueSubmit}>
                    <Grid container spacing={2}>
                        <List component="ul"
                        id="venue-select">
                        {venues.map((venue) => (
                            <ListItem key={venue.id} component="div">
                                <ListItemButton onClick={(event) =>handleListItemClick(event,venue.id as number)}>
                                    <ListItemText primary={venue.name} secondary={venue.address}/>
                                    </ListItemButton>
                            </ListItem>
                            
                        ))}
                        </List>
                    </Grid>
                    <Button type="submit"
                    fullWidth
                    variant="contained"
                    sx={{ mt: 3, mb: 2 }}
                    >OK</Button>
                    </Box>
                </Box>
            </Container>
        </ThemeProvider>
    )
}
export default SelectVenueComponent;