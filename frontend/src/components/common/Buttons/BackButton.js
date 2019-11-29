import React, {Component} from 'react';
import {
  StyleSheet,
  Button,
  View,
} from 'react-native';

export default class BackButton extends Component {
  constructor(props) {
    super(props)
  }

  render() {
    return (
      <View style={styles.button}>
        <Button
          title="Back"
          onPress={this.props.previous}
          color='black'
        />
      </View>
    );
  }
}



const styles = StyleSheet.create({
  button: {
    position: 'absolute',
    bottom: 0,
    backgroundColor: 'black',
    width: '110%'
  },
});
