import React, { Component } from 'react';
import { Text, View } from 'react-native';
import { Card, Button, Icon } from 'react-native-elements'
import MapRoute from './../MapRoute/MapRoute';

class ImageCard extends Component{
  constructor(props){
    super(props)
    this.state = {
      Itinerary: this.props.common
    }
<<<<<<< HEAD
  }

  handleButtonPress = () => {
    return <Text>Hello</Text>
=======
>>>>>>> f0ca4c1b9275c4154d4ca9092a2e2eaff9da0704
  }

  handleButtonPress = () => {
    return <Text>Hello</Text>
  }

  //COST AND TIME

  render(){
    console.log("EventCard", this.props);
    return (
      <Card
        title={this.props.title}
        image={this.props.image}>
        <Text style={{marginBottom: 10}}>{this.props.text}</Text>
        <Text>cost: {"$".repeat(this.props.cost)}</Text>
        <Text>People tyically spend {this.props.time.hour + " hours, " + this.props.time.minute + " minutes here"}</Text>
        <View style={{flexDirection: 'row', flex: 2, justifyContent: 'space-between'}}>
          <Button
        icon={<Icon name='code' color='#ffffff'/>}
        title='ADD EVENT'/>
        <Button
        icon={<Icon name='code' color='#ffffff'/>}
        title='DELETE EVENT'/></View>
        <View style={{ flex: 1, justifyContent: 'center', paddingTop: 5}}>
        <Button
        icon={<Icon name='code' color='#ffffff'/>}
        title='MAP'
        onPress={this.handleButtonPress}/></View>
      </Card>
    );
  }
}

export default ImageCard;
