import React from 'react';
import { View, Text } from 'react-native';
import CardsContainer from '../sections/CardsContainer';
import CreateItinerary from '../navigation/CreateItinerary';
import axios from 'axios';

const CHECK_ITINERARY = "http://localhost:8080/api/v1/checkItinerary";
const VIEW_ITINERARY = "http://localhost:8080/api/v1/viewItinerary";

export default class Itinerary extends React.Component {
  constructor(props){
    super(props)
    this.state = {
      Itinerary: null
    }
  }

  componentDidMount(){
    this.fetch();
  }

  fetch(){
    console.log("lol")
    axios.get(CHECK_ITINERARY)
    .then(res => {
      console.log("res.data", res.data);
      if(res.data){
        this.fetchItinerary();
      } else {
        this.setState({
          Itinerary: false
        })
      }
    })
    .catch(e => console.log(e))
  }

  fetchItinerary(){
    axios.get(VIEW_ITINERARY)
    .then(res => {
      console.log("res.data", res.data);
      console.log("res.data.Itinerary", res.data.Itinerary)
      this.setState({
        ItineraryData: res.data,
        Itinerary: true
      });
     })
    .catch(e => console.log(e))
  }

  render(){
    if(this.state.Itinerary === null){
      return(
        <View>
          <Text>LOADING!</Text>
        </View>
      )
    }
    return(
        this.state.Itinerary ? <CardsContainer common={this.state.ItineraryData}/> : <CreateItinerary/>
    );
  }
}
