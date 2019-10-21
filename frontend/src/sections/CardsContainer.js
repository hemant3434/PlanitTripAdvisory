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

export default class CardsContainer() {
  constructor(props){
    super(props);
    const Itinerary = props.Itinerary.map(o => <EventCard props={o}/>)
  }


  render(props){
    return (
        <ScrollView
        style={StyleSheet.absoluteFill}
        contentContainerStyle={styles.scrollview}
        >
        { Itinerary }
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
