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
import ImageCard from './../components/Cards/ImageCard';
import TravelCard from './../components/Cards/TravelCard';

export default function CardsContainer() {
  return (
      <ScrollView
      style={StyleSheet.absoluteFill}
      contentContainerStyle={styles.scrollview}
      >
          <ImageCard title="Toronto" image={require("../images/toronto.jpg")} text="lmao"/>
          <TravelCard title="Car" icon="flight-takeoff" subtitle="69 mins"/>
          <ImageCard title="Scarborough" image={require("../images/toronto.jpg")} text="lmao"/>
          <TravelCard title="Plane" icon="flight-takeoff" subtitle="33 mins"/>
          <ImageCard title="China" image={require("../images/toronto.jpg")} text="420"/>
      </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
