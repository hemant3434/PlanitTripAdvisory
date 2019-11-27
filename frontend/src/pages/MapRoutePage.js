import React from 'react';
import { View, ScrollView, Text } from "react-native";
import MapRoute from '../components/common/MapRoute/MapRoute';
import BackButton from '../components/common/Buttons/BackButton';

class MapRoutePage extends React.Component {
  constructor(props){
    super(props);
    this.state = {
        Itinerary: this.props.common,
      };
  }

  render() {
    return (
      <ScrollView contentContainerStyle={{flexGrow: 1, justifyContent: "center", padding: 10}}>
        <MapRoute common={this.props.common}/>
        <BackButton previous={this.props.previous}/>
      </ScrollView>
    )
  }
}

export default MapRoutePage
