import React from 'react';
import { View } from 'react-native';
import CardsContainer from '../sections/CardsContainer';
import CreateItinerary from '../navigation/CreateItinerary';
import axios from 'axios';

const CHECK_ITINERARY = "localhost/api/v1/checkItinerary";
const VIEW_ITINERARY = "localhost/api/v1/checkItinerary";

export default class Itinerary extends React.Component {
  constructor(props){
    super(props)
  }

  componentDidMount(){
    this.fetch();
  }

  fetch(){
    axios.get(CHECK_ITINERARY)
    .then(res => {
      console.log("res.data", res.data);
      if(res.data.exists){
        this.fetchItinerary();
        this.setState({
          Itinerary: true,
        })
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
      this.setState({
        ItineraryData: res.data.Itinerary
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
      <View style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
        { this.state.Itinerary ? <CardsContainer common={this.state.ItineraryData}/> : <CreateItinerary/> }
      </View>
    );
  }
}
