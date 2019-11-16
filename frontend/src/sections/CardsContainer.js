import React from 'react';
import { StyleSheet, View, ScrollView  } from 'react-native';
import {
  Text,
} from "native-base";
import EventCard from './../components/common/Cards/EventCard';

export default class CardsContainer extends React.Component{

  constructor(props){
    super(props);
    this.state = {
      isLoading: this.props.isLoading,
      Itinerary: this.props.common
    }
  }


  render(props){
    return (
        <ScrollView
        style={StyleSheet.absoluteFill}
        contentContainerStyle={styles.scrollview}>
        { !this.state.isLoading ? this.props.common.map(o => <EventCard common={o}/>):<Text>Loading</Text> }
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
