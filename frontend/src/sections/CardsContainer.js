import React from 'react';
import { StyleSheet, View, ScrollView  } from 'react-native';
import axios from 'axios';
import {
  Container,
  Header,
  Title,
  Content,
  Button,
  Icon,
  List,
  ListItem,
  Text,
  Left,
  Right,
  Body
} from "native-base";
import EventCard from './../components/Cards/EventCard';

const tmp = [{
  type: "event",
  title: "soccer",
  image: require("../images/toronto.jpg"),
  description: "some text"
},
{
  type: "transportation",
  title: "car",
  subtitle: "30 minutes",
  icon: "flight-takeoff"
},
{
  type: "event",
  title: "soccer",
  image: require("../images/toronto.jpg"),
  description: "some text"
}];

export default class CardsContainer extends React.Component{
  componentDidMount() {
    this.fetchData();
  }

  fetchData(){
    axios.get('http://localhost:8082/api/v1/getItinerary')
      .then(res => {
        console.log("res", res);
        const data = res.data;
        console.log("res.data", data);
        this.setState({
          isLoading: false,
          Itinerary: data
        });
      })
      .catch(error => console.log(error));
  }

  constructor(props){
    super(props);
    this.state = {
      isLoading: true,
      Itinerary: null
    }
  }


  render(props){
    return (
        <ScrollView
        style={StyleSheet.absoluteFill}
        contentContainerStyle={styles.scrollview}>
        { !this.state.isLoading ? this.state.Itinerary.map(o => <EventCard common={o}/>):<Text>Loading</Text> }
        </ScrollView>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
