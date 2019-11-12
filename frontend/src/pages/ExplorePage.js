import React from 'react';
import { View, ScrollView, Text } from "react-native";
import EventsCardsContainer from "./../sections/EventsCardsContainer";

class ExplorePage extends React.Component {
  constructor(props){
    super(props);
    this.state = {
        search: "",
      };
  }

  updateSearch = search => {
    this.setState({ search });
  };

  render() {
    const { search } = this.state;
    return (
        <View style={{flex: 10, justifyContent: 'center', alignItems: 'center'}}>
            <EventsCardsContainer/>
        </View>);
  }
}

export default ExplorePage
