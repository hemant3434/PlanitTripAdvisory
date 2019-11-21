import React from 'react';
import { View, Text } from 'react-native';
import CardsContainer from '../sections/CardsContainer';
import CreateItinerary from '../navigation/CreateItinerary';
import axios from 'axios';

const CHECK_ITINERARY = "http://192.168.0.189:8080/api/v1/checkItinerary";
const VIEW_ITINERARY = "http://192.168.0.189:8080/api/v1/viewItinerary";


export default class Itinerary extends React.Component {
  constructor(props){
    super(props)
    this.state = {
      Itinerary: null,
      loading: true,
      ItineraryData: null
    }
  }

  componentDidMount(){
    this.fetch();
  }

  fetch(){
    console.log("Calling fetch")
    axios.get(CHECK_ITINERARY)
    .then(res => {
      console.log("CHECK_ITINERARY.data", res.data);
      this.setState({
        Itinerary: res.data
      })
      if(res.data){
        this.fetchItinerary();
      }
    })
    .catch(e => {
      console.log(e.response);
      console.log(e.message);
      console.log(e.request);
    })
  }

  fetchItinerary(){
    console.log("FETCHING NOW");
    axios.get(VIEW_ITINERARY)
    .then(res => {
      console.log("FETCHED ITINERARY", res.data);
      this.setState({
        ItineraryData: res.data,
        loading: false
      });
     })
    .catch(e => console.log(e.response))
  }

  render(){
    console.log("RENDER STATE", this.state);
    switch(this.state.Itinerary){
      case null:
        return (
          <View>
            <Text>Loading 1</Text>
          </View>
        );
      case true:
        if(this.state.loading){
          return (
            <View>
              <Text>Loading 2</Text>
            </View>
          );
        }
        return <CardsContainer common={this.state.ItineraryData}/>
      default:
       return <CreateItinerary/>
    }
  }
}
