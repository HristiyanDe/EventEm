import React, { useState } from 'react';
import { API_TICKETS_PATH } from '../../constants/apiConstants'; 
import axios from 'axios';
import { useAuth } from '../../auth/AuthContext';
import { TicketRequest } from '../../models/TicketRequest';
import LocationCityIcon from '@mui/icons-material/LocationCity';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { ThemeProvider } from '@mui/material/styles';
import { defaultTheme } from '../RegisterComponent/RegisterComponent';
import { SelectEventComponent } from '../EventComponent/selectEventComponent';
import { FindEventRequest } from '../../models/FindEventRequest';
const TicketForm: React.FC = () => {
const [formData, setFormData] = useState<TicketRequest>({
    name: '',
    eventId: '',
    price: 0,
    });
const { token } = useAuth();
const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    console.log(formData);
    event.preventDefault();
    try {
        console.log(token);
        const response = await axios.post(API_TICKETS_PATH, formData, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        });
        console.log(response.data);
    } catch (error) {
        console.error(error);
    }
};
const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const {name, value} = e.target;
    setFormData({
        ...formData,
        [name]:value,
    });
};
const [selectedEvent, setSelectedEvent] = useState<FindEventRequest | null>(null);
const onEventSelect = (event: FindEventRequest | null) => {
              setFormData((prevData) => ({
                ...prevData,
                eventId: event ? event.id : '',
              }));
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
            alignItems: 'center',
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
            <LocationCityIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Add a venue
          </Typography>
          <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
            <TextField
              margin="normal"
              required
              fullWidth
              id="create-ticket-name"
              label="Ticket name"
              name="name"
              autoComplete="name"
              autoFocus
              onChange={handleInputChange}
            />
            <SelectEventComponent onEventSelect={onEventSelect} />
            <TextField
              margin="normal"
              required
              fullWidth
              name="price"
              label="Ticket price"
              id="create-ticket-price"
              autoComplete="ticket-price"
              onChange={handleInputChange}
            />
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
                Create Ticket
            </Button>
            <Grid container>
              <Grid item xs>
                <Link href="#" variant="body2">
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
        };
export default TicketForm;