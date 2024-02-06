import axios from "axios";
import { Venue } from "../models/dtos/VenueDTO";
import { API_VENUES_PATH } from "../constants/apiConstants";
class VenueService{
    async getVenuesFiltered(name?: string, address?: string, city?: string): Promise<Venue[]>{
        const response = await axios.get<Venue[]>(API_VENUES_PATH,{
            params:{
                venueName: name,
                venueCity: city,
                venueAddress: address

            }
        })
        return response.data;
    }
}
export const venueService = new VenueService();