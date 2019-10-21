import React from 'react';
import { StyleSheet, View, ScrollView  } from 'react-native';
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
  constructor(props){
    super(props);
    this.state = {
      Itinerary: tmp
    }
  }


  render(props){
    return (
        <ScrollView
        style={StyleSheet.absoluteFill}
        contentContainerStyle={styles.scrollview}
        >
        { this.state.Itinerary.map(o => <EventCard common={o}/>) }
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
